package zs.xmx.mvpframe.model

import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 12:28
 * @本类描述	  用户业务逻辑接口
 * @内容说明
 *
 */
interface IUserModel {

    //注册
    fun register(lastUrl: String, params: HashMap<String, Any>, event_tag: String)

    //登录
    fun login(lastUrl: String, params: HashMap<String, Any>, event_tag: String)

    //首页test
    fun showData(lastUrl: String, params: HashMap<String, Any>, event_tag: String)
}