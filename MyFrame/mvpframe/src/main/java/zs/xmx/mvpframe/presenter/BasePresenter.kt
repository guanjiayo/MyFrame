package zs.xmx.mvpframe.presenter

import android.content.Context
import com.hwangjr.rxbus.RxBus
import com.trello.rxlifecycle.LifecycleProvider
import zs.xmx.mvpframe.utils.NetWorkUtils
import zs.xmx.mvpframe.view.IBaseView
import javax.inject.Inject


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 18:16
 * @本类描述	  BasePresenter
 * @内容说明  1.这里直接使用泛型的方式直接拿到View层回调
 *
 * todo 后续只保留这个类
 */

/**
 * 1. 使用泛型方式拿到 View层的引用(子类传IBaseView的子类)
 * 2. 子类Presenter 使用实现的方式拿到各自M层的引用
 * 3. 子类Presenter 进行 M层 和 V层 的数据绑定(1.接口回调方式,2.BusEvent方式)
 */
open class BasePresenter<T : IBaseView> {

    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    //todo 优化
    //lateinit var mView: WeakReference<T>
    lateinit var mView: T

    //Dagger注入，Rx生命周期管理
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    init {
        registerBus()
    }

    //注册RxBus
    private fun registerBus() {
        RxBus.get().register(this)
    }

    //反注册RxBus
    //todo 这里需要研究下怎么把这个移到每个子类的默认OnDestroy()方法里面使用
    open fun unRegisterBus() {
        RxBus.get().unregister(this)
    }

    /**
     * 检查网络是否可用
     * <p>
     * 业务逻辑:
     * 1.
     * 2. 请求网络数据
     */
    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isConnected(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }

}