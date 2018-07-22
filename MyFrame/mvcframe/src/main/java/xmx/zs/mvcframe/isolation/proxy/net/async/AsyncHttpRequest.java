package xmx.zs.mvcframe.isolation.proxy.net.async;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Map;

import cz.msebera.android.httpclient.Header;
import xmx.zs.mvcframe.isolation.proxy.net.HttpRequest;
import xmx.zs.mvcframe.isolation.proxy.net.ICallback;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/4/30 21:33
 * @本类描述	  AsyncHttpRequest网络框架
 * @内容说明   网络框架实现类(其他网络框架类似实现)
 *
 *
 */
public class AsyncHttpRequest implements HttpRequest {

    private final AsyncHttpClient mAsyncHttpClient;

    public AsyncHttpRequest() {
        mAsyncHttpClient = new AsyncHttpClient();

    }

    @Override
    public void get(String url, Map<String, String> params, final ICallback callback) {
        StringBuffer sb = new StringBuffer("?");
        //拼接 get请求 url
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        sb.deleteCharAt(sb.length() - 1); //删除最后一个 & 符号
        url += sb.toString();

        mAsyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callback.onSuccess(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onFailure(statusCode, error);
            }
        });

    }

    @Override
    public void post(String url, Map<String, String> params, final ICallback callback) {
        //todo post 参考上面实现,这里先不写了
    }


}
