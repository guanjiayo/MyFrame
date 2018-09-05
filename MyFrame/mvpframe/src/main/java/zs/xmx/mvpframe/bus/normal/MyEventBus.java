package zs.xmx.mvpframe.bus.normal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/23.
 * <p>
 * 使用:
 * 1.注册:  MyEventBus.getDefault().register(this);
 * 2.       MyEventBus.getDefault().post(); 发送消息
 * 3.       定义要接受的方法
 *
 * @Subscribe("main")
 * private void test(传过来的参数){
 * //doSomething...
 * }
 * 4.在onDestory()反注册,防止内存泄漏 MyEventBus.getDefault().unregister(this);
 * 5.退出App时,调用这里的clear()方法,把缓存数组清空掉
 * <p>
 * 这个类注册在BaseActivity或HomeActivity,一次注册,全部受益
 * <p>
 * 用法和EventBus基本一致,在有@Subscribe()方法的类上注册即可
 * <p>
 * <p>
 * //todo 自定义异常告诉使用前需要注册
 * //todo 遗忘的话看图
 */

public class MyEventBus {

    //缓存数组
    private static final Map<Class, List<SubscriberMethod>> METHOD_CACHE = new HashMap<>();
    //执行数组
    private static final Map<String, List<Subscription>>    SUBSCRIBES   = new HashMap<>();
    //反注册辅助数组
    private static final Map<Class, List<String>>           REGISTERS    = new HashMap<>();

    private static volatile MyEventBus instance;

    private MyEventBus() {
    }

    public static MyEventBus getDefault() {
        if (null == instance) {
            synchronized (MyEventBus.class) {
                if (null == instance) {
                    instance = new MyEventBus();
                }
            }
        }
        return instance;
    }


    /**
     * 注册
     * <p>
     * 1.找到所有的订阅者方法,并将相关参数存入缓存数组
     * 2.拿到标签,并存入到执行表数组
     *
     * @param subscriber
     */
    public void register(Object subscriber) {
        Class<?> subscriberClass = subscriber.getClass();
        //找到被Subscribe注解的函数 并存到缓存表
        List<SubscriberMethod> subscriberMethods = findSubscribe(subscriberClass);

        //为了方便注销
        List<String> labels = REGISTERS.get(subscriberClass);
        if (null == labels) {
            labels = new ArrayList<>();
        }

        //加入注册集合  key:标签 value:对应标签的所有函数
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            //拿到标签
            String label = subscriberMethod.getLabel();
            if (!labels.contains(label)) {
                labels.add(label);
            }
            //执行表数据是否存在
            List<Subscription> subscriptions = SUBSCRIBES.get(label);
            if (subscriptions == null) {
                subscriptions = new ArrayList<>();
                //
                SUBSCRIBES.put(label, subscriptions);
            }
            Subscription newSubscription = new Subscription(subscriber, subscriberMethod);
            subscriptions.add(newSubscription);
        }

        REGISTERS.put(subscriberClass, labels);
    }

    /**
     * 找到所有类中被@Subscribe的方法
     *
     * @param subscriberClass
     * @return
     */
    private List<SubscriberMethod> findSubscribe(Class<?> subscriberClass) {
        //先看缓存中是否有 被@subscribe 的 subscribeMethods方法
        List<SubscriberMethod> subscriberMethods = METHOD_CACHE.get(subscriberClass);
        //如果缓存中没有,反射遍历Object里的所有方法
        if (null == subscriberMethods) {
            subscriberMethods = new ArrayList<>();
            //遍历函数
            Method[] methods = subscriberClass.getDeclaredMethods();
            for (Method method : methods) {
                Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                if (null != subscribeAnnotation) {
                    //注解上的标签
                    String[] values = subscribeAnnotation.value();
                    //参数
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (String value : values) {
                        method.setAccessible(true);
                        SubscriberMethod subscriberMethod = new SubscriberMethod(value, method,
                                parameterTypes);
                        subscriberMethods.add(subscriberMethod);
                    }
                }
            }
            //缓存
            METHOD_CACHE.put(subscriberClass, subscriberMethods);
        }
        return subscriberMethods;
    }


    public void clear() {
        METHOD_CACHE.clear();
        SUBSCRIBES.clear();
        REGISTERS.clear();
    }

    /**
     * 发送事件给订阅者
     *
     * @param label
     * @param params
     */
    public void post(String label, Object... params) {
        // 通过标签拿到执行数组
        List<Subscription> subscriptions = SUBSCRIBES.get(label);
        if (null == subscriptions) {
            return;
        }
        for (Subscription subscription : subscriptions) {
            SubscriberMethod subscriberMethod = subscription.getSubscribeMethod();
            //执行函数需要的参数类型数组
            Class[] paramterClass = subscriberMethod.getParameterTypes();
            //要传的参数,需要一一对应顺序
            Object[] realParams = new Object[paramterClass.length];
            if (null != params) {
                for (int i = 0; i < paramterClass.length; i++) {
                    //传进来的参数 类型是method需要的类型
                    if (i < params.length && paramterClass[i].isInstance(params[i])) {
                        realParams[i] = params[i];
                    } else {
                        realParams[i] = null;
                    }
                }
            }
            try {
                subscriberMethod.getMethod().invoke(subscription.getSubscriber(), realParams);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /**
     * 反注册
     * <p>
     * 1.通过反注册辅助数组,获得对应的所有标签
     * 2.清理执行数组,根据标签获得集合,判断与本次注销的是否为同一个对象.(只注销当前对象)
     * 3.通过标签删除对应的@Subscribes方法
     *
     * @param subscriber
     */
    public void unregister(Object subscriber) {
        //找到该对象中所有的订阅标签
        List<String> labels = REGISTERS.remove(subscriber.getClass());
        if (null != labels) {
            for (String label : labels) {
                //获得执行表中对应label的所有函数
                List<Subscription> subscriptions = SUBSCRIBES.get(label);
                if (null != subscriptions) {
                    Iterator<Subscription> iterator = subscriptions.iterator();
                    while (iterator.hasNext()) {
                        Subscription subscription = iterator.next();
                        //对象是同一个才删除
                        if (subscription.getSubscriber() == subscriber) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }
}
