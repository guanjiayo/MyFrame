package zs.xmx.mvpframe.net.Rx;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import zs.xmx.mvpframe.utils.init.ConfigKeys;
import zs.xmx.mvpframe.utils.init.ProjectInit;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 11:03
 * @本类描述	  Retrofit 封装(完成后能够链式调度使用)
 * @内容说明   使用RxJava方式
 *
 */
public final class RxRestCreator {
    /**
     * 产生一个全局的Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String   BASE_URL        = ProjectInit.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();

    }

    private static final class OkHttpHolder {
        private static final long         TIME_OUT       = 60;//连接超时时间
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }


    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    /**
     * 提供接口让调用者得到Retrofit对象
     */
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
