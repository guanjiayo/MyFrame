package zs.xmx.mvpframe.bus.rx;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/8 0:12
 * @本类描述	  RxBus
 * @内容说明   todo 基础RxBus,然后在这上面修改
 *
 * 使用说明:  看RxUse.md文档
 *
 * //todo 实际开发还是使用RXBus官方那个
 *
 */
public final class RxBus {

    private final FlowableProcessor<Object> mBus;

    private final Consumer<Throwable> mOnError = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) {
            MyRxUtils.logE(throwable.toString());
        }
    };

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault() {
        return Holder.BUS;
    }

    public void post(final Object event) {
        post(event, "", false);
    }

    public void post(final Object event, final String tag) {
        post(event, tag, false);
    }

    public void postSticky(final Object event) {
        post(event, "", true);
    }

    public void postSticky(final Object event, final String tag) {
        post(event, tag, true);
    }

    private void post(final Object event,
                      final String tag,
                      final boolean isSticky) {
        MyRxUtils.requireNonNull(event, tag);

        MyRxTagMessage msgEvent = new MyRxTagMessage(event, tag);
        if (isSticky) {
            MyRxCacheUtils.getInstance().addStickyEvent(msgEvent);
        }
        mBus.onNext(msgEvent);
    }

    public <T> void subscribe(final Object subscriber,
                              final Callback<T> callback) {
        subscribe(subscriber, "", false, null, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final String tag,
                              final Callback<T> callback) {
        subscribe(subscriber, tag, false, null, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final Scheduler scheduler,
                              final Callback<T> callback) {
        subscribe(subscriber, "", false, scheduler, callback);
    }

    public <T> void subscribe(final Object subscriber,
                              final String tag,
                              final Scheduler scheduler,
                              final Callback<T> callback) {
        subscribe(subscriber, tag, false, scheduler, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final Callback<T> callback) {
        subscribe(subscriber, "", true, null, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final String tag,
                                    final Callback<T> callback) {
        subscribe(subscriber, tag, true, null, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final Scheduler scheduler,
                                    final Callback<T> callback) {
        subscribe(subscriber, "", true, scheduler, callback);
    }

    public <T> void subscribeSticky(final Object subscriber,
                                    final String tag,
                                    final Scheduler scheduler,
                                    final Callback<T> callback) {
        subscribe(subscriber, tag, true, scheduler, callback);
    }

    private <T> void subscribe(final Object subscriber,
                               final String tag,
                               final boolean isSticky,
                               final Scheduler scheduler,
                               final Callback<T> callback) {
        MyRxUtils.requireNonNull(subscriber, tag, callback);

        final Class<T> typeClass = MyRxUtils.getTypeClassFromCallback(callback);

        final Consumer<T> onNext = new Consumer<T>() {
            @Override
            public void accept(T t) {
                callback.onEvent(t);
            }
        };

        if (isSticky) {
            final MyRxTagMessage stickyEvent = MyRxCacheUtils.getInstance().findStickyEvent(typeClass, tag);
            if (stickyEvent != null) {
                Flowable<T> stickyFlowable = Flowable.create(new FlowableOnSubscribe<T>() {
                    @Override
                    public void subscribe(FlowableEmitter<T> emitter) {
                        emitter.onNext(typeClass.cast(stickyEvent.mEvent));
                    }
                }, BackpressureStrategy.LATEST);
                if (scheduler != null) {
                    stickyFlowable = stickyFlowable.observeOn(scheduler);
                }
                Disposable stickyDisposable = MyRxFlowableUtils.subscribe(stickyFlowable, onNext, mOnError);
                MyRxCacheUtils.getInstance().addDisposable(subscriber, stickyDisposable);
            } else {
                MyRxUtils.logW("sticky event is empty.");
            }
        }
        Disposable disposable = MyRxFlowableUtils.subscribe(
                toFlowable(typeClass, tag, scheduler), onNext, mOnError
        );
        MyRxCacheUtils.getInstance().addDisposable(subscriber, disposable);
    }

    private <T> Flowable<T> toFlowable(final Class<T> eventType,
                                       final String tag,
                                       final Scheduler scheduler) {
        Flowable<T> flowable = mBus.ofType(MyRxTagMessage.class)
                .filter(new Predicate<MyRxTagMessage>() {
                    @Override
                    public boolean test(MyRxTagMessage MyRxTagMessage) {
                        return MyRxTagMessage.isSameType(eventType, tag);
                    }
                })
                .map(new Function<MyRxTagMessage, Object>() {
                    @Override
                    public Object apply(MyRxTagMessage MyRxTagMessage) {
                        return MyRxTagMessage.mEvent;
                    }
                })
                .cast(eventType);
        if (scheduler != null) {
            return flowable.observeOn(scheduler);
        }
        return flowable;
    }

    public void unregister(final Object subscriber) {
        MyRxCacheUtils.getInstance().removeDisposables(subscriber);
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

    public interface Callback<T> {
        void onEvent(T t);
    }
}
