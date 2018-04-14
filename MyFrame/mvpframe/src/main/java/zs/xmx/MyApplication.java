package zs.xmx;

import android.app.Application;
import android.content.Context;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/9
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
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
        context=this;
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
