package zs.xmx.mvpframe.view.impl.activity

import zs.xmx.mvpframe.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/17 17:30
 * @本类描述	  LoginActivity的 IView接口
 * @内容说明
 *
 */
interface ILoginView : IBaseView {
    /**
     * 登录结果回调
     */
    fun onLoginResult(result: Any)
}
