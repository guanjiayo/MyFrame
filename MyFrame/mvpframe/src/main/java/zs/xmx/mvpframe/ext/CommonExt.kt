package zs.xmx.mvpframe.ext


import android.view.View
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zs.xmx.mvpframe.base.rx.BaseSubscriber

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 2:56
 * @本类描述	  Kotlin 的通用扩展
 * @内容说明
 *
 */

/*
    扩展RxJava 的 Observable执行
    优化RxJava常见的使用方式

    //todo 目前修改下框架,完美使用RxJava.
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle()) //Rxlifecycle方法绑定Observable的生命周期
            .subscribe(subscriber)
}


/*
    扩展点击事件
    使用: btn_regiest.onClick(this)
 */
fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法(lambda形式)
    使用: btn_regiest.onClick{ 点击后续操作  }
 */
fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}
