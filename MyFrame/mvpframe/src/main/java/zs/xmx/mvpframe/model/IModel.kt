package zs.xmx.mvpframe.model

import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/26 11:55
 * @本类描述	  基类Model接口
 * @内容说明
 *
 */
interface IModel {

    fun loadDataFromNet(lastUrl: String, params: HashMap<String, Any>)
}
