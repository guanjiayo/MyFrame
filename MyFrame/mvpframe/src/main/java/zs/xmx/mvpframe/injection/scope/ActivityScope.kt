package zs.xmx.mvpframe.injection.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/*
    自定义Scope
    用于声明 Activity级别 作用域
 */
@Scope
@Retention(RUNTIME)
annotation class ActivityScope
