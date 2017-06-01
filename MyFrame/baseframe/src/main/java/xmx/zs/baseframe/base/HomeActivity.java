package xmx.zs.baseframe.base;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import xmx.zs.baseframe.R;
import xmx.zs.baseframe.utils.ActivityManager;
import xmx.zs.baseframe.utils.FragmentFactory;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/2 0:18
 * @本类描述	  Fragment 管理类
 * @内容说明   1.单Activity继承BaseActivity
 *            2.单Activity+多Fragment用HomeActivity管理Fragment
 *
 *
 * @本类功能
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class HomeActivity extends BaseActivity {
    //点击时间
    private   long                                   mPreClickTime;
    protected android.support.v4.app.FragmentManager fragmentManager;
    private   Toolbar                                mToolbar;
    private int[] titleId = {R.string.MainFragment};

    @Override
    protected int getContentViewId() {
        //主页面的布局
        return R.layout.activity_home;
    }


    @Override
    protected void initView() {

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitle("Title");
        mToolbar.setTitleTextColor(Color.rgb(0, 30, 0));
        mToolbar.setSubtitle("Subtitle");
        mToolbar.setSubtitleTextColor(Color.rgb(0, 30, 0));

        initFragment();
    }

    @Override
    protected void initData() {

    }

    /**
     * 清空数组里所有的Fragment,防止Fragment重叠问题
     * <p>
     * 再设置初始化时显示的Fragment
     * <p>
     * 然后在子Fragment里使用fragment.isAdded()方法判断Fragment是否已经添加到Activity
     * true:显示Fragment
     * false:添加Fragment
     */
    private void initFragment() {
        //清空数组里所有的Fragment,防止Fragment重叠问题
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < titleId.length; i++) { //遍历所有Fragment
            Fragment fragment = fragmentManager.findFragmentByTag(i + "");
            if (fragment != null) { //有fragment才清空
                fragmentTransaction.remove(fragment);
            }
        }
        fragmentTransaction.commit();
        //设置初始化时显示的Fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, FragmentFactory.getFragment(0), "0").commit();


    }


    /**
     * DrawerLayout 开关事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();


    }


    /**
     * 后面用来处理Fragment事件的
     *
     * @param fragmentTag
     * @return
     */
    public BaseFragment getFragment(String fragmentTag) {
        return (BaseFragment) fragmentManager.findFragmentByTag(fragmentTag);
    }

    /**
     * 移除fragment
     * popBackStack() 回退一个Fragment,当等于1时,finish()
     */
    public void removeFragment() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }

    }

    /**
     * 移除Fragment(保留左侧Fragment)
     * <p>
     * 原来的方法是回退一个栈,但是这种方法会让LeftFragment 也移除掉
     * <p>
     * 解决:
     * 1.新建一个事务创建左侧的Fragment
     * 2.保留两个Fragment
     *
     * @param leftFragmentTag 左侧FragmentTag
     */
    public void removeFragment(String leftFragmentTag) {
        //当大于2个Fragment且这两个Fragment中包含LeftFragment时,才回退一个Fragment
        if (fragmentManager.getBackStackEntryCount() > 2 && (fragmentManager.getFragments().contains(getFragment
                (leftFragmentTag)))) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }

    }

   /* */

    /**
     * 如果同时要显示两种Fragment 用这个(类似SlideMenu 类型)
     *//*
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 2) {
            //TODO 要改成Fragment依赖的Activity
            if (this instanceof HomeActivity) {
                if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的间隔大于2s中
                    showToast("再按一次,退出APP");
                    mPreClickTime = System.currentTimeMillis();
                    return;
                } else {

                    ActivityManager.getInstance().AppExit(this);
                }
            }

        } else {
            removeFragment("LeftFragment");
        }

    }*/
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            //TODO 要改成Fragment依赖的Activity
            if (this instanceof HomeActivity) {
                if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的间隔大于2s中
                    showToast("再按一次,退出APP");
                    mPreClickTime = System.currentTimeMillis();
                    return;
                } else {

                    ActivityManager.getInstance().AppExit(this);

                }
            }

        } else {
            removeFragment();
        }

    }


}
