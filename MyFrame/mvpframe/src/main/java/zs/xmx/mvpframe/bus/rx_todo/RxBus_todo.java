package zs.xmx.mvpframe.bus.rx_todo;

import android.support.v4.util.ArrayMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/15 12:37
 * @本类描述	  RxBus_todo 数据总线
 * @内容说明   todo 有需要的话添加背压模式
 *            todo 线程切换不方便(看老师写的处理一下?)
 *            todo 不知道是那个观察者发送过来的(后面加标签处理)
 *
 * 使用 下面三个方法一般写在同一类中
 *      在要接受数据的方法添加@RxSubscribe注解
 *      RxBus_todo.getDefault().register()
 *      RxBus_todo.getDefault().unRegister()
 *
 *      在要发数据的类使用
 *      因为现在没标签区分,直接post javaBean
 *      RxBus_todo.getDefault().post()
 *
 *  todo https://github.com/AndroidKnife/RxBus 注解优化
 *  todo https://github.com/wzgiceman/Rxbus 使用方式优化成和EventBus一样
 *  todo 目前直接使用rx那个文件里的
 *
 *
 *
 */
public class RxBus_todo {

    private final           Subject<Object>                    bus;
    private final           ArrayMap<String, List<Disposable>> disposableArray;
    private static volatile RxBus_todo                         instance;

    private RxBus_todo() {
        bus = PublishSubject.create().toSerialized();
        disposableArray = new ArrayMap<>();

    }

    /**
     * 单例
     *
     * @return
     */
    public static RxBus_todo getDefault() {
        if (instance == null) {
            synchronized (RxBus_todo.class) {
                if (instance == null) {
                    instance = new RxBus_todo();
                }
            }
        }
        return instance;
    }


    /**
     * 注册RxBus
     *
     * @param observable
     */
    public void register(final Object observable) {
        boolean isRegisterSuccess = false;
        final String subscriptionKey = observable.getClass().getName();
        //反射获取方法列表
        Method[] methods = observable.getClass().getMethods();
        for (Method method : methods) {
            //如果方法不存在Subscribe注解则不做处理
            if (!method.isAnnotationPresent(RxSubscribe_todo.class))
                continue;
            final Method subscriptionMethod = method;
            //获取方法参数类型，即：MessageEvent.class
            Class<?> key = method.getParameterTypes()[0];
            //订阅MessageEvent.class类型实例，ofType作用已filter相近
            Disposable disposable = bus.ofType(key)
                    .subscribeOn(Schedulers.io()) //todo 测试线程处理
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object value) throws Exception {
                            try {
                                //反射赋值，即传递事件值
                                subscriptionMethod.setAccessible(true);
                                subscriptionMethod.invoke(observable, value);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(subscriptionKey + " isn't allowed to register!");
                            } catch (InvocationTargetException e) {
                                throw new RuntimeException(subscriptionKey + " isn't allowed to register!");
                            }
                        }
                    });
            //存储订阅实例列表，用于后续解除注册
            List<Disposable> disposables;
            if (disposableArray.containsKey(subscriptionKey)) {
                disposables = disposableArray.get(subscriptionKey);
            } else {
                disposables = new ArrayList<>();
            }
            disposables.add(disposable);
            disposableArray.put(subscriptionKey, disposables);
            isRegisterSuccess = true;
        }
        //如果注册的类中不存在Subscribe注解方法，则抛出异常提醒
        if (!isRegisterSuccess)
            throw new RuntimeException(subscriptionKey + " has no any RxBuxSubscribe Event!");
    }

    /**
     * 反注册RxBus
     *
     * @param observable
     */
    public void unRegister(Object observable) {
        String subscriptionKey = observable.getClass().getName();
        if (!disposableArray.containsKey(subscriptionKey))
            return;
        List<Disposable> disposables = disposableArray.get(subscriptionKey);
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                //如果已订阅，则取消订阅
                if (!disposable.isDisposed())
                    disposable.dispose();

            }

            disposableArray.remove(subscriptionKey);
        }
    }

    /**
     * 发送事件
     *
     * @param event
     */
    public void post(Object event) {
        bus.onNext(event);
    }


}
