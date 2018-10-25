package zs.xmx.mvpframe.base.fragment

import android.os.Bundle
import android.view.View
import zs.xmx.mvpframe.base.BaseApplication
import zs.xmx.mvpframe.injection.component.ActivityComponent
import zs.xmx.mvpframe.injection.component.DaggerActivityComponent
import zs.xmx.mvpframe.injection.module.ActivityModule
import zs.xmx.mvpframe.injection.module.LifecycleProviderModule
import zs.xmx.mvpframe.presenter.BasePresenter
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/19 15:09
 * @本类描述	  需要使用Dagger的BaseFragment
 * @内容说明   1.泛型T这样使用,就能拿到其他子类传过来的XXPresenter
 *
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment() {

    //presenter层的引用,用于后面子类的直接调用
    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActivityInjection()
        initComponentInject()
    }

    /**
     * 构建ActivityComponent和activityModule联系
     * <p>
     * 其中ActivityComponent依赖AppComponent
     */
    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent((mContext.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(mContext))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    /**
     * 让子类实现Dagger2的绑定,方便子类inject其他组件
     */
    protected abstract fun initComponentInject()

}
