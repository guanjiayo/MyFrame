package zs.xmx.mvpframe.presenter

import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import zs.xmx.mvpframe.model.IUserModel
import zs.xmx.mvpframe.view.impl.activity.IRegisterView
import java.util.*
import javax.inject.Inject


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/10 18:06
 * @本类描述	  注册逻辑的Presenter层
 * @内容说明   V层引用放到BasePresenter
 *
 */
class RegisterPresenter @Inject constructor() : BasePresenter<IRegisterView>() {

    //M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun register(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {

        mModel.register(lastUrl, params, event_tag)

    }

    /**
     * 处理json数据
     *
     * @param params
     */
    @Subscribe(tags = arrayOf(Tag("RegisterActivity")))
    fun parseRegiestData(params: String) {
        mView.onRegisterResult(params)
    }

}
