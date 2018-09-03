package zs.xmx.mvpframe.base

import android.graphics.Color
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*

import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.adapter.HomeVPAdapter
import zs.xmx.mvpframe.utils.factory.FragmentFactory


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/2 0:18
 * @本类描述	  Fragment 管理类
 * @内容说明   1.单Activity继承BaseActivity
 *            2.单Activity+多Fragment用HomeActivity管理Fragment
 *
 *
 * @本类功能  这个框架的首页使用ViewPager+fragment懒加载
 *
 * ---------------------------------
 * @新增内容
 *
 */
class HomeActivity : BaseActivity() {

    private var fragmentManager: FragmentManager? = null
    private var mToolbar: Toolbar? = null
    private val titleId = intArrayOf(R.string.MainFragment, R.string.TwoFragment,
            R.string.ThreeFragment, R.string.FourFragment, R.string.FiveFragment)
    private val fragments = arrayListOf<Fragment>()


    override fun setContentViewId(): Int {
        return R.layout.activity_home
    }


    override fun initView() {

        mToolbar = findViewById(R.id.main_toolbar)
        mToolbar!!.title = "Title"
        mToolbar!!.setTitleTextColor(Color.rgb(0, 30, 0))
        mToolbar!!.subtitle = "Subtitle"
        mToolbar!!.setSubtitleTextColor(Color.rgb(0, 30, 0))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //initFragment()
        initViewPager()
    }

    private fun initViewPager() {
        for (i in titleId.indices) {
            fragments.add(FragmentFactory.createFragment(i)!!)
        }
        viewPager.adapter = HomeVPAdapter(supportFragmentManager, fragments)
        /**
         *  让ViewPager直接缓存所有页面,
         *  ViewPager就不存在预加载的情况了
         */
        viewPager.offscreenPageLimit = titleId.size
    }

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                //todo 切换到mainFragment
                showToast("home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_show -> {
                viewPager.currentItem = 1
                //todo
                showToast("show")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_find -> {
                viewPager.currentItem = 2
                //todo
                showToast("find")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shop -> {
                viewPager.currentItem = 3
                //todo
                showToast("shop")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                viewPager.currentItem = 4

                //todo
                showToast("me")
                return@OnNavigationItemSelectedListener true
            }

        }

        false

    }


    override fun initData() {

    }

    /**
     * 1.清空数组里所有的Fragment,防止Fragment重叠问题
     * 2.再设置初始化时显示的Fragment
     * 注一: 需要预加载的话可以直接add()全部,show()或hide()用来显示或隐藏
     * 注二: 根据点击状态几个RadioButton的index,add对应的Fragment
     *
     *
     * 然后在子Fragment里使用fragment.isAdded()方法判断Fragment是否已经添加到Activity
     * //todo 看抽到哪个位置可一起判断
     * true:显示Fragment
     * false:添加Fragment
     *
     *
     * 使用hide()或show()方法时会触发onHiddenChanged(),可用作刷新数据
     *
     * @Override public void onHiddenChanged(boolean hidden) {
     *    super.onHiddenChanged(hidden);
     *    if (hidden){
     *       //Fragment隐藏时调用
     *    }else {
     *        //Fragment显示时调用
     *    }
     * }
     */
    //todo 直接改造成一次性全部加载,在FragmentFactory判断isAdded()
    //todo 这里是不结合ViewPager的写法
    private fun initFragment() {
        //清空数组里所有的Fragment,防止Fragment重叠问题
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in titleId.indices) { //遍历所有Fragment
            val fragment = fragmentManager.findFragmentByTag("$i")
            if (fragment != null) { //有fragment才清空
                fragmentTransaction.remove(fragment)
            }
            //设置初始化时显示的Fragment
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_main, FragmentFactory.createFragment(i)!!, i.toString()).commit()
        }

    }


    /**
     * DrawerLayout 开关事件
     */
    override fun initEvent() {

    }

    override fun onClick(v: View) {

    }

    /**
     * 后面用来处理Fragment事件的
     *
     * @param fragmentTag
     * @return
     */
    private fun getFragment(fragmentTag: String): BaseFragment {
        return fragmentManager!!.findFragmentByTag(fragmentTag) as BaseFragment
    }

    /**
     * 移除fragment
     * popBackStack() 回退一个Fragment,当等于1时,finish()
     */
    fun removeFragment() {
        if (fragmentManager!!.backStackEntryCount > 1) {
            fragmentManager!!.popBackStack()
        } else {
            finish()
        }

    }

    /**
     * 移除Fragment(保留左侧Fragment)
     *
     *
     * 原来的方法是回退一个栈,但是这种方法会让LeftFragment 也移除掉
     *
     *
     * 解决:
     * 1.新建一个事务创建左侧的Fragment
     * 2.保留两个Fragment
     *
     * @param leftFragmentTag 左侧FragmentTag
     */
    fun removeFragment(leftFragmentTag: String) {
        //当大于2个Fragment且这两个Fragment中包含LeftFragment时,才回退一个Fragment
        if (fragmentManager!!.backStackEntryCount > 2 && fragmentManager!!.fragments.contains(getFragment(leftFragmentTag))) {
            fragmentManager!!.popBackStack()
        } else {
            finish()
        }

    }

}
