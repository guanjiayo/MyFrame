package zs.xmx.netframe.base;

import android.app.Application;
import android.content.Context;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/15
 * @本类描述	  Application类
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //app和基础baseurl
        RxRetrofitApp.init(this);
        mContext = getApplicationContext();
    }


}
