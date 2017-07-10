package zs.xmx.netframe;
/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/15
 * @本类描述	  接口参数接口---POST方式
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface IHttpPost {

    /**
     * POST @Field
     * <p>
     * 单个参数
     */
    @FormUrlEncoded
    @POST("weather")
   // Call<String> postData(@Field("city") String city, @Field("key") String key);
    Observable<String> postData(@Field("city") String city, @Field("key") String key);
    //Call<String> postData(@Query("city") String city, @Query("key") String key); //@Query方式

    /**
     * POST @Body
     * <p>
     * 多个参数(以对象的形式提交)
     */
    @FormUrlEncoded
    @POST("weather")
    Observable<String> postDataList(@FieldMap Map<String, String> params);
    //Call<String> postDataList(@QueryMap Map<String, String> params); //@Query方式


}
