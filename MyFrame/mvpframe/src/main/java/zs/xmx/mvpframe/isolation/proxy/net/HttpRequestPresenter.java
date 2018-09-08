package zs.xmx.mvpframe.isolation.proxy.net;

import java.util.Map;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/4/30 22:00
 * @本类描述	  网络框架代理(中间层)
 * @内容说明
 *
 * 使用:  HttpRequestPresenter.init(new AsyncHttpRequest());
          HttpRequestPresenter.getDefault().get(xx);
 *
 * //todo 写一个自定义异常,告诉我们先要init,再 getInstant()
 *
 */
public class HttpRequestPresenter implements HttpRequest {

    private                 HttpRequest          httpRequest;
    //volatile 不让线程创建副本
    private static volatile HttpRequestPresenter instance;

    private HttpRequestPresenter(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public static void init(HttpRequest httpRequest) {
        if (null == instance) {
            synchronized (HttpRequestPresenter.class) {
                if (null == instance) {
                    instance = new HttpRequestPresenter(httpRequest);
                }
            }
        }
    }

    public static HttpRequestPresenter getInstance() {
        return instance;
    }

    @Override
    public void get(String url, Map<String, String> params, ICallback callback) {
        httpRequest.get(url, params, callback);
    }

    @Override
    public void post(String url, Map<String, String> params, ICallback callback) {
        httpRequest.post(url, params, callback);
    }
}
