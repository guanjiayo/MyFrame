package xmx.zs.mvcframe.base.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyEventBus 订阅者Subscribe要使用的注解
 */
@Target(ElementType.METHOD) //注解用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时的级别(我们要使用反射)
public @interface Subscribe {
    //我们的订阅者标签可以有多个
    //如 @Subscribe({Constants.A1,Constants.A2})
    //那么EventBus发送A1或A2,被@Subscribe注解的方法都能接收到
    String[] value();
}
