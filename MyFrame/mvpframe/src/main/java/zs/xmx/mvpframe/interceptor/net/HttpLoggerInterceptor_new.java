package zs.xmx.mvpframe.interceptor.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 10:52
 * @本类描述	  网络请求日志拦截器
 * @内容说明
 *
 */
public class HttpLoggerInterceptor_new implements Interceptor {

    private String newHost = "127.0.0.1";
    private String path1   = "/test/upload/img";
    private String path2   = "/test/upload/voice";
    private String TAG     = "TestInterceptor";

    public static String requestBodyToString(RequestBody requestBody) throws IOException {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readUtf8();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        HttpUrl url = request.url();
        //http://127.0.0.1/test/upload/img?userName=xiaoming&userPassword=12345
        String scheme = url.scheme();//  http https
        String host = url.host();//   127.0.0.1
        String path = url.encodedPath();//  /test/upload/img
        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345

        RequestBody body = request.body();
        String bodyToString = requestBodyToString(body);


        Log.e(TAG, scheme);
        Log.e(TAG, host);
        Log.e(TAG, path);
        Log.e(TAG, query);

        if (response != null) {
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";

            Log.e(TAG, response.code() + ' '
                    + response.message() + ' '
                    + response.request().url() + ' '
                    + bodySize
            );

            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                Log.e(TAG, headers.name(i) + ": " + headers.value(i));
            }


        }


        return chain.proceed(request);
    }


}
