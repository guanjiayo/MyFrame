package zs.xmx.mvpframe.weight

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import zs.xmx.mvpframe.R

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/24 15:59
 * @本类描述	  BottomNavBar
 * @内容说明   二次封装com.ashokvarma.bottomnavigation.BottomNavigationBar
 *            方便管理和使用
 *
 *  todo 后期使用类似方式调用自定义控件,方便维护
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    //购物车Badge
    private val mCartBadge: TextBadgeItem
    //发现Badge
    private val mFindBadge: ShapeBadgeItem

    init {
        //首页Item
        val homeItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_home)
                .setActiveColorResource(R.color.colorAccent)//选中的颜色
                .setInActiveColorResource(R.color.colorPrimary)//未选中的颜色


        //秀场Item
        val showItem = BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, R.string.title_show)
                .setActiveColorResource(R.color.blue)


        //发现Item
        val findItem = BottomNavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_find)
                .setActiveColorResource(R.color.orange)
        /**
         * 初始化ShapeBadgeItem
         */
        mFindBadge = ShapeBadgeItem()
        mFindBadge
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColorResource(R.color.orange)
                .setGravity(Gravity.TOP or Gravity.END)
        findItem.setBadgeItem(mFindBadge)


        //购物车Item
        val cartItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_shop)
                .setActiveColorResource(R.color.brown)
        /**
         * 初始化TextBadgeItem
         */
        mCartBadge = TextBadgeItem()
        mCartBadge
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.material_blue_grey_900)

        cartItem.setBadgeItem(mCartBadge)


        //我的Item
        val meItem = BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_me)
                .setActiveColorResource(R.color.teal)

        setMode(BottomNavigationBar.MODE_FIXED) //
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .addItem(homeItem)
                .addItem(showItem)
                .addItem(findItem)
                .addItem(cartItem)
                .addItem(meItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    /*
       检查购物车Tab是否显示标签
    */
    fun setCartBadgeCount(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }

    /*
        检查发现Tab是否显示标签
     */
    fun checkFindBadge(isVisible: Boolean) {
        if (isVisible) {
            mFindBadge.show()
        } else {
            mFindBadge.hide()
        }
    }


}