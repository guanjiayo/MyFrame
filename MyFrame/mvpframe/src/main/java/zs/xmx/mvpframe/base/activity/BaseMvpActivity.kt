package zs.xmx.mvpframe.base.activity

import android.os.Bundle
import zs.xmx.mvpframe.base.BaseApplication
import zs.xmx.mvpframe.injection.component.ActivityComponent
import zs.xmx.mvpframe.injection.component.DaggerActivityComponent
import zs.xmx.mvpframe.injection.module.ActivityModule
import zs.xmx.mvpframe.injection.module.LifecycleProviderModule
import zs.xmx.mvpframe.presenter.BasePresenter
import zs.xmx.mvpframe.weight.ProgressLoadingDialog
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/18 21:39
 * @本类描述   需要使用Dagger的BaseActivity
 * @内容说明   1.单Activity继承BaseActivity(含Dagger)
 *            2.单Activity+多Fragment用HomeActivity
 *            3.泛型T这样使用,就能拿到其他子类传过来的XXPresenter
 */

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity() {

    //presenter层的引用,用于后面子类的直接调用
    //Dagger 的一种注入方式
    //这里 @Inject 变量名
    //BasePresenter的子类: class XXPresenter @Inject constructor() : BasePresenter<IXXView>() 相当于module的provide声明
    // 然后 Component 声明要@Inject的类
    //最后就相当于 XXActivity里面实现了 mPresenter=XXPresenter()
    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mProgressLoadingDialog: ProgressLoadingDialog

//    /**
//     * 构建Component和Module联系
//     *
//     *
//     * userComponent.inject(this);
//     */
//    val userComponent: UserComponent
//        get() = DaggerUserComponent.builder()
//                .activityComponent(mActivityComponent)
//                .userModule(UserModule())
//                .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initComponentInject()
        mProgressLoadingDialog= ProgressLoadingDialog.create(this)
    }


    /**
     * 构建ActivityComponent和activityModule联系
     * <p>
     * 其中ActivityComponent依赖AppComponent
     */
    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    /**
     * 让子类实现Dagger2的绑定,方便子类inject其他组件
     */
    protected abstract fun initComponentInject()

}
