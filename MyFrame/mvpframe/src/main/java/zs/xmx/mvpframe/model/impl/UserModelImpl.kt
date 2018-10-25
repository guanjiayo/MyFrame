package zs.xmx.mvpframe.model.impl

import com.hwangjr.rxbus.RxBus
import zs.xmx.mvpframe.model.IUserModel
import zs.xmx.mvpframe.net.retrofit_normal.RestClient
import java.util.*
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 12:30
 * @本类描述	  用户业务逻辑接口实现
 * @内容说明
 *
 */
class UserModelImpl @Inject constructor() : IUserModel {

    /**
     * 首页数据test
     */
    override fun showData(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {
        loadDataFromNet(lastUrl, params, event_tag)
    }

    /**
     * 登录
     */
    override fun login(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {
        loadDataFromNet(lastUrl, params, event_tag)
    }

    /**
     * 注册
     */
    override fun register(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {

        loadDataFromNet(lastUrl, params, event_tag)

    }

    /**
     * 真正获取请求网络的数据,请求成功的result
     */
    private fun loadDataFromNet(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {
        // todo 1.可以Rxbus 传到Presenter的接受器
//todo  2.老版本一般用接口回调(有内存泄漏问题)
//todo  3. RxJava方式改造,但是不好扩展(要结合Rxlifecycle解决内存泄漏问题)

        RestClient.create()
                .lastUrl(lastUrl)
                .params(params)
                .success { response ->
                    RxBus.get().post(event_tag, response)
                }
                .build()
                .post()
    }

}