package zs.xmx.mvpframe.presenter

import zs.xmx.mvpframe.model.IModel
import zs.xmx.mvpframe.model.impl.IModelImpl
import zs.xmx.mvpframe.view.IView
import java.lang.ref.WeakReference
import java.util.*


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 18:16
 * @本类描述	  BasePresenter
 * @内容说明  todo 看下文档怎么继承带构造函数的类,现在用快速生成的有问题
 *           todo 这里的成员变量子类拿不到,看下什么毛病
 *
 */
abstract class BasePresenter<T : IView>(view: T) {

    protected val TAG = this.javaClass.simpleName

    //1.view 层的引用
    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    var mView: WeakReference<T> = WeakReference(view)
    //2.model 层的引用
    var mModel: IModel = IModelImpl()

    //3.执行UI逻辑操作(将数据和UI捆绑起来)
    /**
     * Model回调回来数据
     *
     * 子类做事件总线拿到数据
     *
     * @param params
     */
    abstract fun parseData(params: String)

    /**
     * P层提供给View层传递数据,M层执行数据的方法
     * @param params
     */
    fun loadDataFromNet(lastUrl: String, params: HashMap<String, Any>) {
        mModel.loadDataFromNet(lastUrl, params)
    }

}