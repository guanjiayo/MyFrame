package zs.xmx;

import android.app.Application;

import java.util.ArrayList;

import okhttp3.Interceptor;
import zs.xmx.utils.init.ProjectInit;

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

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ProjectInit.init(this)
                .withApiHost("http://gank.io/api/")//配置主机名
                .withInterceptors(new ArrayList<Interceptor>())
                .configure();
    }


}
