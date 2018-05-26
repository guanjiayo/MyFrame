package xmx.zs.mvcframe.base.bus;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/4/23.
 * 被注解的方法
 */

public class SubscriberMethod {

    //注解的标签
    private String  label;
    //被注解的方法
    private Method  method;
    //被注解方法参数的类型
    private Class[] parameterTypes;

    public SubscriberMethod(String label, Method method, Class[] parameterTypes) {
        this.label = label;
        this.method = method;
        this.parameterTypes = parameterTypes;
    }

    public String getLabel() {
        return label;
    }

    public Method getMethod() {
        return method;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
