package zs.xmx.mvpframe.presenter

import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import zs.xmx.mvpframe.model.IUserModel
import zs.xmx.mvpframe.model.impl.UserModelImpl
import zs.xmx.mvpframe.view.impl.fragment.IMainView
import java.util.*
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/16 18:36
 * @本类描述	  MainFragPresent
 * @内容说明
 *
 */
class MainFragPresent @Inject constructor(): BasePresenter<IMainView>() {


    fun showData(lastUrl: String, params: HashMap<String, Any>, event_tag: String) {

        //M 层的引用
        //todo 改成Dagger注入方式(常规方式)
        val mModel: IUserModel = UserModelImpl()
        mModel.showData(lastUrl, params, event_tag)

    }

    /**
     * 处理json数据
     *
     * @param params
     */
    @Subscribe(tags = arrayOf(Tag("MainFragment")))
    fun parseRegiestData(params: String) {
        mView.onShowData(params)
    }


}

