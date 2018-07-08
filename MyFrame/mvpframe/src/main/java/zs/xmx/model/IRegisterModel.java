package zs.xmx.model;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/10 17:48
 * @本类描述	  注册数据返回接口
 * @内容说明   通过回调拿到数据
 *
 */
public interface IRegisterModel {

    //    //通过接口回调数据,防止数据没加载完成的尴尬
    //    void loadRegisterData(OnRegisterLoadListener onRegisterLoadListener);
    //
    //    //设置回调接口
    //    interface OnRegisterLoadListener {
    //        void onComplete(String datas);
    //    }

    void loadRegisterData();
}
