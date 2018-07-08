package zs.xmx.lib_permission.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import zs.xmx.lib_permission.PermissionUtils;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    String[] value();

    int requestCode() default PermissionUtils.DEFAULT_REQUEST_CODE;
}
