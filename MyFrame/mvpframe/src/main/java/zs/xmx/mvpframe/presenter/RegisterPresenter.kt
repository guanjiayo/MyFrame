package zs.xmx.mvpframe.presenter

import zs.xmx.mvpframe.model.IModel
import zs.xmx.mvpframe.model.impl.IModelImpl
import zs.xmx.mvpframe.net.Rx.databus.RXEventBus
import zs.xmx.mvpframe.view.IView
import java.lang.ref.WeakReference
import java.util.*


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/10 23:09
 * @本类描述	  注册逻辑的Presenter层
 * @内容说明   V层引用放到BasePresenter
 *
 */
class RegisterPresenter<T : IView>(view: T) {
    //1.view 层的引用
    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    private val mView: WeakReference<T> = WeakReference(view)
    //2.model 层的引用
    var mModel: IModel = IModelImpl()

    //3.执行UI逻辑操作(将数据和UI捆绑起来)

    /**
     * Model回调回来数据
     *
     * @param params
     */
    @RXEventBus
    fun parseData(params: String) {
        mView.get()?.urlRequestSuccess(params)

    }

    /**
     * P层提供给View层传递数据,M层执行数据的方法
     *
     * @param params
     */
    fun loadDataFromNet(params: HashMap<String, Any>) {
        mModel.loadDataFromNet(params)
    }

}
