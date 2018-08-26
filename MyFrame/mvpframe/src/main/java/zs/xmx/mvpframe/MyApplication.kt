package zs.xmx.mvpframe

import android.app.Application
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.utils.LifeCycleManager
import zs.xmx.mvpframe.utils.init.ProjectInit
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/9
 * @本类描述	  全局配置信息
 * @内容说明
 * @补充内容
 *
 * ---------------------------------
 * @更新时间
 * @新增内容
 *
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(LifeCycleManager.mCallbacks)
        ProjectInit.init(this)
                .withApiHost(MyConstant.baseUrl)//配置主机名
                .withInterceptors(ArrayList())
                .configure()
        initSDK()
    }

    private fun initSDK() {


    }


}
