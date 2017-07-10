package zs.xmx.netframe.api;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import retrofit2.Retrofit;
import rx.Observable;
import zs.xmx.netframe.IHttpPost;
import zs.xmx.netframe.constants.MyConstants;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/15
 * @本类描述	  请求参数实体类
 * @内容说明
 * @补充内容   具体setXXX() 看BaseApi
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class SubjectPostApi extends BaseApi {

    //设置接口要传入得参数,可自定义不同类型
    private String city    = "中山";
    private String baseUrl = MyConstants.baseUrl;
    private String key     = MyConstants.urlKey;

    /**
     * 默认初始化需要给定回调和rx周期类
     * 可以额外设置请求设置加载框显示，回调等（可扩展）
     */
    public SubjectPostApi() {
        setBaseUrl(baseUrl);//baseUrl
        setCache(false);
        setMethod("POST");//接口标记

    }

    /**
     * 结合RxJava构建Retrofit
     *
     * @param retrofit
     * @return
     */
    @Override
    public Observable getObservable(Retrofit retrofit) {
        IHttpPost httpPost = retrofit.create(IHttpPost.class);
        return httpPost.postData(city, key);
    }


}
