package zs.xmx.mvpframe.injection.component

import dagger.Component
import zs.xmx.mvpframe.injection.module.UserModule
import zs.xmx.mvpframe.injection.scope.ProvideComponentScope
import zs.xmx.mvpframe.ui.activity.LoginActivity
import zs.xmx.mvpframe.ui.activity.RegisterActivity

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 18:20
 * @本类描述	  用户业务层的 Component
 * @内容说明   主要用于优化 各个XXPresenter 的 val mModel: IUserModel = UserModelImpl()
 *  todo 组件化应该放到对应模块里面
 */

@ProvideComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
}