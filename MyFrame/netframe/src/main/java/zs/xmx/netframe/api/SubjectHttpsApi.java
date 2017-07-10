package zs.xmx.netframe.api;
/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/17
 * @本类描述	  Https 配置
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import retrofit2.Retrofit;
import rx.Observable;
import zs.xmx.netframe.IHttpsGet;
import zs.xmx.netframe.constants.MyConstants;

public class SubjectHttpsApi extends BaseApi {

    public SubjectHttpsApi() {
        setBaseUrl(MyConstants.baseUrl);
        setMethod("HTTPS");
        setCache(false);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        IHttpsGet httpsGet = retrofit.create(IHttpsGet.class);
        return httpsGet.getData("广州", MyConstants.urlKey);
    }
}
