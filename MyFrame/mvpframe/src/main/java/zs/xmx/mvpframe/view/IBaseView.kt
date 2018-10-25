package zs.xmx.mvpframe.view

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/21 19:21
 * @本类描述	   BaseView
 * @内容说明   公共的View层回调
 *
 */
interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun onError(errorInfo: String)

}