package zs.xmx.mvpframe.injection.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/*
    自定义Scope
    用于声明 其他业务逻辑层的Component级别 作用域
    (如这里的userComponent)
 */
@Scope
@Retention(RUNTIME)
annotation class ProvideComponentScope
