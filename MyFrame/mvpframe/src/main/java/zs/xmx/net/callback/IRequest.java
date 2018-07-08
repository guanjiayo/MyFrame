package zs.xmx.net.callback;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/14 12:15
 * @本类描述	  网络请求进度(如文件上传下载开始和结束)
 * @内容说明
 *
 */
public interface IRequest {

    void onRequestStart();
    void onRequestEnd();
}
