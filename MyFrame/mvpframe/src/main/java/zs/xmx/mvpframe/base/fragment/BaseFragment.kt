package zs.xmx.mvpframe.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import zs.xmx.mvpframe.base.activity.HomeActivity
import zs.xmx.mvpframe.utils.ToastUtils
import zs.xmx.mvpframe.view.IBaseView
import zs.xmx.mvpframe.weight.ProgressLoadingDialog

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/1 20:11
 * @本类描述	  Fragment 基类
 * @内容说明  Fragment管理交由依赖的Activity管理
 *           //TODO Fragment的增删该操作写一个工具类FragmentUtils操作
 *           //todo 包括栈问题
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
abstract class BaseFragment : LazyFragment(), IBaseView {

    /**
     * TAG 这里定义TAG,后面继承的类都能直接使用
     */
    open val TAG = this.javaClass.simpleName
    /**
     * 上下文
     */
    lateinit var mContext: HomeActivity

    //加载进度的ProgressDialog
    private lateinit var mProgressLoadingDialog: ProgressLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        //获取上下文
        mContext = (activity as HomeActivity?)!!
        super.onCreate(savedInstanceState)
    }

    /**
     * Fragment中,用onCreateView()拿到布局xml
     * <p>
     * ButterKnife.bind(this, rootView);
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(mContext, setLayoutID(), null)
    }

    /**
     * Fragment 布局文件ID
     *
     * @return
     */
    protected abstract fun setLayoutID(): Int

    /**
     * Fragment中,onViewCreated()初始化View对象
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }


    /**
     * 初始化View对象
     *
     *
     * 如果在onCreateView()实现了ButterKnife.bind(),可不重写该方法
     *
     * @param rootView onViewCreated()传过来的rootView,用于rootView.findViewById()
     */
    open fun initView(rootView: View) {

    }

    /**
     * 首次加载页面被调用
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // 只要Activity 没有销毁   该方法不会重复调用
        setBaseConfig()
        setScreen()
        initData()
        initEvent()
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 设置一些屏幕相关的操作
     */
    private fun setScreen() {
        mProgressLoadingDialog = ProgressLoadingDialog.create(mContext)
    }

    /**
     * 设置一些基本属性
     */
    private fun setBaseConfig() {

    }

    /**
     * 子类覆盖此方法来完成数据的初始化
     */
    open fun initData() {

    }


    /**
     * 子类覆盖此方法来完成事件的设置
     */
    open fun initEvent() {

    }

    /**
     * Fragment使用show()/hide()方法会被调用
     *
     * @param hidden true隐藏 : false显示
     *
     *
     * Fragment生命周期跟随Activity
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

    }

    open fun showToast(msg: String) {
        ToastUtils.showToast(mContext, msg)
    }
    open fun showLongToast(msg: String) {
        ToastUtils.showToast(mContext, msg,Toast.LENGTH_LONG)
    }


    override fun showLoading() {
        mProgressLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mProgressLoadingDialog.hideLoading()
    }

    override fun onError(errorInfo: String) {
        showToast(errorInfo)
    }

}
