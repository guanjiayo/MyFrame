package zs.xmx.mvpframe.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import zs.xmx.mvpframe.base.BaseApplication
import javax.inject.Singleton

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 19:42
 * @本类描述	  AppModule
 * @内容说明   Application级别Module
 *            用于全局参数的注入(该项目用于全局上下文)
 *
 */

/**
 * @param context
 * 让注入的全局上下文,由继承于BaseApplication的子类传进来
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.context
    }
}