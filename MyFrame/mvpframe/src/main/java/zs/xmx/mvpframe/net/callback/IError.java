package zs.xmx.mvpframe.net.callback;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:08
 * @本类描述	  网络请求失败返回的接口
 * @内容说明
 *
 */
public interface IError {
    void onError(int code, String msg);
}
