package zs.xmx.mvpframe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/2 14:32
 * @本类描述	  首页ViewPager适配器
 * @内容说明
 *
 */
class HomeVPAdapter(fm: androidx.fragment.app.FragmentManager, private val fragments: List<androidx.fragment.app.Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}