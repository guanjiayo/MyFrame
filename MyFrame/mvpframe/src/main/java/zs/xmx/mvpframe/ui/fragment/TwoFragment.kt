package zs.xmx.mvpframe.view.fragment

import android.util.Log
import android.view.View
import android.widget.TextView
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.fragment.BaseFragment


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
class TwoFragment : BaseFragment() {


    override fun setLayoutID(): Int {
        return R.layout.fragment_two
    }

    override fun initView(view: View) {
        val tv = view.findViewById<View>(R.id.textView) as TextView
        tv.text = "two"
    }

    override fun lazyLoad() {
        val message = "two" + (if (isInit) "已经初始并已经显示给用户可以加载数据" else "没有初始化不能加载数据") + ">>>>>>>>>>>>>>>>>>>"
        Log.d(TAG, message)
    }


    override fun stopLoad() {
        Log.d(TAG, "two" + "已经对用户不可见，可以停止加载数据")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d(TAG, "two$hidden")
    }

}
