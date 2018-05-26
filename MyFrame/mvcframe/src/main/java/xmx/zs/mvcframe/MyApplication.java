package xmx.zs.mvcframe;

import android.app.Application;
import android.content.Context;

import xmx.zs.mvcframe.utils.ActListManager;
import xmx.zs.mvcframe.utils.GlobalUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/11
 * @本类描述	  全局Application类
 * @内容说明   1.第三SDK初始化
 *             2.部分工具类初始化(Toast,dp转换,需要上下文之类的)
 *             3.主题背景,Activity管理类等等
 *             4.捕获app全局异常
 *             5.定义全局Context
 *
 * @补充内容    1.如果工具类过多,可以写一个Global类用于初始化工具类以减少Application类的代码量
 *             2.注意Application类不能保存数据
 *
 * ---------------------------------
 * @更新时间
 * @新增内容
 *
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //File file = new File(Environment.getExternalStorageDirectory().getPath(), "app6");
        //Log.i("MyApplication", "onCreate: " + file.getAbsolutePath());
       // Debug.startMethodTracing("app6");
        context = this;
        GlobalUtils.init(this);
        registerActivityLifecycleCallbacks(ActListManager.mCallbacks);
        initSDK();
       // Debug.stopMethodTracing();
    }

    private void initSDK() {
        /**UMeng SDK**/
        //如果开发者调用kill或者exit之类的方法杀死进程，
        // 请务必在此之前调用MobclickAgent.onKillProcess(Context context)方法，用来保存统计数据
        //其他统计方法看友盟的官方文档
        //UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "59a2abe8aed179634c0016c2");
        //UMConfigure.setEncryptEnabled(false);//日志是否加密
        //UMConfigure.setLogEnabled(true);//LOG 是否输出

    }

    /**
     * 全局上下文
     * <p>
     * 使用: Application.getAPPContext()
     * <p>
     * 避免部分不能使用getApplicationContext()的情况
     *
     * @return context
     */
    public static Context getAPPContext() {
        return context;
    }


}
