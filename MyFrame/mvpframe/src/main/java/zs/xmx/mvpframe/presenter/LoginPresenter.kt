package zs.xmx.mvpframe.presenter


import zs.xmx.mvpframe.bus.rx_todo.RxSubscribe_todo
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.model.IModel
import zs.xmx.mvpframe.model.impl.IModelImpl
import zs.xmx.mvpframe.view.IView
import java.lang.ref.WeakReference
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/10 23:09
 * @本类描述	  登录逻辑的Presenter层
 * @内容说明
 *
 */
class LoginPresenter<T : IView>(view: T) {
    //1.view 层的引用
    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    private var mView: WeakReference<T> = WeakReference(view)
    //2.model 层的引用
    var mModel: IModel = IModelImpl()


    //3.执行UI逻辑操作(将数据和UI捆绑起来)
    /**
     * Model回调回来数据
     *
     * @param params
     */
    @RxSubscribe_todo
    fun parseData(params: String) {
        //todo 解析数据,封装库这里先不管
        mView.get()!!.urlRequestSuccess(params)
    }

    /**
     * P层提供给View层传递数据,M层执行数据的方法
     * @param params
     */
    fun loadDataFromNet(params: HashMap<String, Any>) {
        //TODO 后面看下封装一个BasePresenter,把公共方法抽取
        mModel.loadDataFromNet(MyConstant.LOGIN, params)
    }


}
