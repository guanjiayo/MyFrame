package zs.xmx.mvpframe.bus.normal;

/**
 * Method 反射要用到的Object对象
 */
public class Subscription {

    private SubscriberMethod subscriberMethod;//被注解的方法对象
    private Object           subscriber;//执行表中标签对应的实例对象

    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }

    public SubscriberMethod getSubscribeMethod() {
        return subscriberMethod;
    }

    public Object getSubscriber() {
        return subscriber;
    }
}
