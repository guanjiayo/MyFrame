package zs.xmx.netframe;
/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/15
 * @本类描述	  接口参数接口---GET方式
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface IHttpGet {
    /**
     * Get @Query
     * <p>
     * 单个参数
     */
    @GET("weather")
    Observable<String> getData(@Query("city") String city, @Query("key") String key);

    /**
     * Get @Query
     * <p>
     * 多个参数
     */
    @GET("weather")
    Observable<String> getDataList(@QueryMap Map<String, String> params);


}
