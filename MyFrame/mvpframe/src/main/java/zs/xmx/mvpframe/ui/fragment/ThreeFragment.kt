package zs.xmx.mvpframe.view.fragment

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
class ThreeFragment : BaseFragment() {


    override fun setLayoutID(): Int {
        return R.layout.fragment_three
    }

    override fun initView(view: View) {
        val tv = view.findViewById<View>(R.id.textView) as TextView
        tv.text = "three"
    }

    override fun lazyLoad() {

    }

}
