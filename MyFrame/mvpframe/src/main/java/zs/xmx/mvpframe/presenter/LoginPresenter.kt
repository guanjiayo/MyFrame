package zs.xmx.mvpframe.presenter

import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import zs.xmx.mvpframe.model.IUserModel
import zs.xmx.mvpframe.view.impl.activity.ILoginView
import java.util.*
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/10 18:06
 * @本类描述	  登录逻辑的Presenter层
 * @内容说明   记得在Activity反注册
 *  todo 这边的数据实现类需要改造下(绑定RxLifecycle,Dialog放进去)
 */
class LoginPresenter @Inject constructor() : BasePresenter<ILoginView>() {

    //M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun login(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {
        mView.showLoading()
        mModel.login(lastUrl, params, event_tag)
    }


    /**
     * 处理json数据
     * 这里使用了TAG,因此我们不需要创建对应的IView接口
     *
     * @param params
     */
    @Subscribe(tags = arrayOf(Tag("LoginActivity")))
    fun parseLoginData(params: String) {
        //todo 这里一般把结果处理完,结果回调即可,如mView.onLoginResult("登录成功")
        mView.hideLoading()
        mView.onLoginResult(params)
    }

}
