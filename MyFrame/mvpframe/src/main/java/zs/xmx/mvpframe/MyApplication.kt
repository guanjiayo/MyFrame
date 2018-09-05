package zs.xmx.mvpframe

import android.app.Application
import com.mob.MobSDK
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.interceptor.net.HttpLoggerInterceptor
import zs.xmx.mvpframe.interceptor.net.HttpUrlInterceptor
import zs.xmx.mvpframe.utils.LifeCycleManager
import zs.xmx.mvpframe.utils.init.ProjectInit

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
        //注册生命周期管理
        registerActivityLifecycleCallbacks(LifeCycleManager.mCallbacks)
        //基础配置 todo 后面traceView测试启动时间
        ProjectInit.init(this)
                .withApiHost(MyConstant.BASE_URL)//配置主机名
                .withInterceptors(initInterceptor())
                .configure()
        initSDK()
    }

    /**
     * 初始化项目中要用到的拦截器
     */
    private fun initInterceptor(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        //网络请求拦截器
        val httpRequestInterceptor = HttpLoggingInterceptor(HttpLoggerInterceptor())
        httpRequestInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //Url重定向拦截器
        //HttpUrlInterceptor()

        interceptors.add(httpRequestInterceptor)
        interceptors.add(HttpUrlInterceptor())
        return interceptors

    }

    /**
     * 初始化项目中要用到的SDK
     */
    private fun initSDK() {
        //todo 参考这个Logger库优化自己的
        Logger.init("LogHttpInfo")
                .hideThreadInfo()
                .methodOffset(2)


        //初始化掌淘SDK
        MobSDK.init(this)

    }


}


