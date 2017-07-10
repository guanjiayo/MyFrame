package zs.xmx.netframe;
/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/17
 * @本类描述	  Https 测试用
 * @内容说明   ${TODO Https}
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IHttpsGet {
    /**
     * Get @Query
     * <p>
     * 单个参数
     */
    @GET("weather")
    Observable<String> getData(@Query("city") String city, @Query("key") String key);
}
