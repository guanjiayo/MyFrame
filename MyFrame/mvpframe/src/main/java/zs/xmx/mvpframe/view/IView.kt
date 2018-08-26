package zs.xmx.mvpframe.view

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/25 17:30
 * @本类描述	  基类View接口
 * @内容说明
 *
 */
interface IView {
    fun urlRequestSuccess(result: Any)

    fun urlRequestFail()
}
