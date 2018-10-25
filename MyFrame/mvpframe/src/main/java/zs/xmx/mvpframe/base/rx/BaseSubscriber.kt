package zs.xmx.mvpframe.base.rx


import rx.Subscriber
import zs.xmx.mvpframe.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 2:41
 * @本类描述	  Rx订阅者默认实现
 * @内容说明   简洁代码,onError()和onCompleted()一般都是同样的处理,抽取出来
 *   todo 先放一下,如果全部使用这种方式,可能换库(网络..)会有点麻烦
 */
open class BaseSubscriber<T>(private val mBaseView: IBaseView) : Subscriber<T>() {

    override fun onCompleted() {
        mBaseView.hideLoading()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable?) {
        mBaseView.hideLoading()
    }


}