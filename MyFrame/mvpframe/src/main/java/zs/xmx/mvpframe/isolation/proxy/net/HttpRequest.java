package zs.xmx.mvpframe.isolation.proxy.net;

import java.util.Map;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/4/30 21:21
 * @本类描述	  网络框架代理接口方法定义
 * @内容说明
 *
 */
public interface HttpRequest {

    /**
     * get 请求
     *
     * @param url
     * @param params
     */
    void get(String url, Map<String, String> params, ICallback callback);

    /**
     * post 请求
     *
     * @param url
     * @param params
     */
    void post(String url, Map<String, String> params, ICallback callback);
}
