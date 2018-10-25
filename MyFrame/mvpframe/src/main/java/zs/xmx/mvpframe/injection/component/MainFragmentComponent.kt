package zs.xmx.mvpframe.injection.component

import dagger.Component
import zs.xmx.mvpframe.injection.module.FragmentModule
import zs.xmx.mvpframe.injection.scope.FragmentScope
import zs.xmx.mvpframe.ui.fragment.MainFragment

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/16 16:07
 * @本类描述	  Fragment Component基类
 * @内容说明  这里对应所有继承了BasesFragment的Component
 *  todo 组件化应该放到对象模块的FragmentModule里面
 */
@FragmentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(FragmentModule::class))
interface MainFragmentComponent {
    /**
     * 可以有多个Inject,需要Inject对象的Act都需要有对应的Dagger绑定
     *
     */
    fun inject(mainFragment: MainFragment)
}
