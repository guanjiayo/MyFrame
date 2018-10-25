package zs.xmx.mvpframe.injection.component

import android.content.Context
import dagger.Component
import zs.xmx.mvpframe.injection.module.AppModule
import javax.inject.Singleton

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 19:32
 * @本类描述	  AppComponent
 * @内容说明   Application级别Component
 *            用于全局参数的注入(该项目用于全局上下文)
 *
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context

}