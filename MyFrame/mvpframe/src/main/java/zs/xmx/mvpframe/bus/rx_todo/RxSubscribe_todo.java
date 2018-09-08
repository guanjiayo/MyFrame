package zs.xmx.mvpframe.bus.rx_todo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/15 12:31
 * @本类描述	  RxBus事件总线接口
 * @内容说明
 *
 */
@Target(ElementType.METHOD) //注解用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时的级别(我们要使用反射)
@Documented
public @interface RxSubscribe_todo {
    //我们的订阅者标签可以有多个
    //如 @RxSubscribe_todo({Constants.A1,Constants.A2})
    //那么EventBus发送A1或A2,被@Subscribe注解的方法都能接收到
    //String[] value();
}
