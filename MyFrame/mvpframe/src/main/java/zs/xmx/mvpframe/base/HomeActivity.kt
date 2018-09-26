package zs.xmx.mvpframe.base

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
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
 * @新增内容  todo 这里快速开发先用这个第三方库,后面自定义一个扩展性更好的,现在这个部分场景不灵活(如虎牙,QQ会动的效果)
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

        initBottomNavigationBar()

        //initFragment()
        initViewPager()
    }

    /**
     * BottomNavigationBar配置
     */
    private fun initBottomNavigationBar() {
        bottom_navigation_bar.setTabSelectedListener(onTabSelectedListener)

        //首页Item
        val homeItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_home)
                .setActiveColorResource(R.color.colorAccent)//选中的颜色
                .setInActiveColorResource(R.color.colorPrimary)//未选中的颜色
        bottom_navigation_bar.addItem(homeItem)

        //秀场Item
        val showItem = BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, R.string.title_show)
                .setActiveColorResource(R.color.blue)
        bottom_navigation_bar.addItem(showItem)

        //发现Item
        val findItem = BottomNavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_find)
                .setActiveColorResource(R.color.orange)
        /**
         * 初始化ShapeBadgeItem
         */
        val shapeBadgeItem = ShapeBadgeItem()
        shapeBadgeItem
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColorResource(R.color.orange)
                .setGravity(Gravity.TOP or Gravity.END)
                .setHideOnSelect(true)
        findItem.setBadgeItem(shapeBadgeItem)
        bottom_navigation_bar.addItem(findItem)

        //购物车Item
        val shopItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_shop)
                .setActiveColorResource(R.color.brown)
        /**
         * 初始化TextBadgeItem
         */
        val numberBadgeItem = TextBadgeItem()
        numberBadgeItem.setBorderWidth(4)
                .setBackgroundColorResource(R.color.material_blue_grey_900)
                .setText("0")
                .setHideOnSelect(true)

        shopItem.setBadgeItem(numberBadgeItem)
        bottom_navigation_bar.addItem(shopItem)

        //我的Item
        val meItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_me)
                .setActiveColorResource(R.color.teal)
        bottom_navigation_bar.addItem(meItem)

        bottom_navigation_bar
                .setMode(BottomNavigationBar.MODE_FIXED) //
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .initialise()


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

    private val onTabSelectedListener = object : BottomNavigationBar.OnTabSelectedListener {
        override fun onTabSelected(position: Int) {
            //未选中->选中
            viewPager.currentItem = position
            when (position) {
                0 -> {
                    showToast("home")
                }
                1 -> {
                    showToast("show")
                }
                2 -> {
                    showToast("find")
                }
                3 -> {
                    showToast("shop")
                }
                4 -> {
                    showToast("me")
                }
            }

        }

        override fun onTabUnselected(position: Int) {
            //选中->未选中
        }

        override fun onTabReselected(position: Int) {
            //选中->选中

        }
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
