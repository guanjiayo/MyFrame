package zs.xmx.mvpframe.base

import android.app.Application
import zs.xmx.mvpframe.injection.component.AppComponent
import zs.xmx.mvpframe.injection.component.DaggerAppComponent
import zs.xmx.mvpframe.injection.module.AppModule

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 19:49
 * @本类描述	  Application基类
 * @内容说明
 *
 */
open class BaseApplication : Application() {

    open lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {

        /**
         * 构建AppComponent和AppModule联系
         */
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}