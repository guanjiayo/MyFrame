package xmx.zs.mvcframe.isolation.proxy.net;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/4/30 21:28
 * @本类描述	  网络代理框架回调
 * @内容说明
 *
 */
public interface ICallback {

    void onSuccess(String result);

    void onFailure(int errorCode, Throwable t);
}
