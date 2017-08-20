package xmx.zs.baseframe;

import android.app.Application;
import android.content.Context;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/11
 * @本类描述	  全局Application类
 * @内容说明   1.第三SDK初始化
 *             2.部分工具类初始化(Toast,dp转换,需要上下文之类的)
 *             3.主题背景,Activity管理类等等
 *             4.捕获app全局异常
 *
 * @补充内容    如果工具类过多,可以写一个Global类用于初始化工具类以减少Application类的代码量
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class MainApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    /**
     * 全局上下文
     * @return context
     */
    public static Context getContext() {
        return context;
    }

}
