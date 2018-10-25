package zs.xmx.mvpframe.view.impl.fragment

import zs.xmx.mvpframe.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 13:08
 * @本类描述	  首页Fragment回调接口
 * @内容说明
 *
 */
interface IMainView :IBaseView{

    fun onShowData(result: Any)
}