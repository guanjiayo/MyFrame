package zs.xmx.presenter.api;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/5
 * @本类描述	  ${TODO}
 * @内容说明   对请求方式和请求参数的封装
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zs.xmx.constant.UrlConstant;
import zs.xmx.model.domian.net.ResponseInfo;

public interface IResponseInfoApi {
    /**
     * 用户登陆
     **/
    //http://localhost:8080/TakeoutService/login?username="itheima"&password="bj"
    @GET(UrlConstant.LOGIN)
    Call<ResponseInfo> login(@Query("username") String username, @Query("password") String password);

}
