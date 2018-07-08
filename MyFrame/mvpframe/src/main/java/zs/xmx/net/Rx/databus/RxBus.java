package zs.xmx.net.Rx.databus;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/15 12:37
 * @本类描述	  数据总线
 * @内容说明
 *
 */
public class RxBus {
    private Set<Object> subscribers;

    /**
     * 注册
     */
    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 取消注册
     */
    public synchronized void unRegister(Object subscriber) {
        subscribers.remove(subscriber);
    }

    //volatile关键字 只保留一个副本
    private static volatile RxBus instance;

    private RxBus() {
        //CopyOnWriteArraySet读写分离的集合(线程安全的集合)
        //将读和写分开成两个数组处理,以保证线程安全
        subscribers = new CopyOnWriteArraySet<>();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 把处理过程包装起来
     * <p>
     * 把数据都放在总线上
     * <p>
     * RxBus.getInstance().chainProcess(new Function() {
     *
     * @Override public Object apply(Object o) throws Exception {
     * //在这里执行请求网络数据操作
     * return null;
     * }
     * });
     */
    public void chainProcess(Function function) {
        Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(function)//在这里进行网络访问
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object data) throws Exception {
                        //data会被传到总线上
                        if (data == null) {
                            return;
                        }
                        send(data);//把数据送到P层
                    }
                });
    }

    public void send(Object data) {
        for (Object subscriber : subscribers) {
            //扫描注解,将数据发送到注册的对象(被注解方法的位置)
            callMethodByAnnotion(subscriber, data);
        }
    }


    private void callMethodByAnnotion(Object target, Object data) {
        //1.得到presenter中的所有方法
        Method[] methodArray = target.getClass().getDeclaredMethods();
        for (int i = 0; i < methodArray.length; i++) {
            try {
                if (methodArray[i].getAnnotation(RegisterRxBus.class) != null) {
                    //如果P层哪个方法上用了我们定义的注解,
                    //就把数据传上去,执行这个方法
                    //todo 这里只写了一个参数,可扩展成传多个参数(每个参数需要遍历验证一下)
                    Class<?> paramType = methodArray[i].getParameterTypes()[0];
                    if (data.getClass().getName().equals(paramType.getName())) {
                        //执行
                        methodArray[i].invoke(target, new Object[]{data});
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
