package zs.xmx.mvpframe.view.fragment

import android.util.Log
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseFragment


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/9 17:22
 * @本类描述	  MainFragment
 * @内容说明
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
class MainFragment : BaseFragment() {

    override fun setLayoutID(): Int {
        return R.layout.fragment_main
    }


    override fun lazyLoad() {
        val message = "Fragment1" + (if (isInit) "已经初始并已经显示给用户可以加载数据" else "没有初始化不能加载数据") + ">>>>>>>>>>>>>>>>>>>"
        Log.d(TAG, message)
    }

    override fun stopLoad() {
        Log.d(TAG, "Fragment1" + "已经对用户不可见，可以停止加载数据")
    }



}
