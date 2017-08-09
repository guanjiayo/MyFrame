package zs.xmx.base;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/9
 * @本类描述	  业务层公共部分代码封装
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zs.xmx.constant.UrlConstant;
import zs.xmx.model.dao.DBHelper;
import zs.xmx.presenter.api.IResponseInfoApi;

public class BasePresenter {
    /**
     * 数据库
     **/
    protected static DBHelper helper;


    /**
     * 网络
     **/
    protected static IResponseInfoApi mResponseInfoApi;

    public BasePresenter() {
        if (mResponseInfoApi == null) {
            /**
             * 访问网络逻辑
             **/
            //第一步:创建Builder,指定baseUrl和数据解析器
            Retrofit.Builder mBuilder = new Retrofit.Builder()
                    .baseUrl(UrlConstant.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create());//Gson解析

            //第二步:创建Retrofit
            Retrofit mRetrofit = mBuilder.build();

            //第三步:指定请求方式(get或post)和参数,通过接口的形式指定
            //第四部:将Retrofit和第三步的联网参数联系起来
            mResponseInfoApi = mRetrofit.create(IResponseInfoApi.class);

        }

        helper = DBHelper.getInstant();
    }
}
