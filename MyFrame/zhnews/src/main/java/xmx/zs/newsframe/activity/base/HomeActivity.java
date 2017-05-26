package xmx.zs.newsframe.activity.base;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import xmx.zs.newsframe.R;
import xmx.zs.newsframe.activity.TwoActivity;
import xmx.zs.newsframe.fragment.BaseFragment;
import xmx.zs.newsframe.fragment.FirstFragment;
import xmx.zs.newsframe.fragment.MainFragment;
import xmx.zs.newsframe.utils.ActivityManager;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/2 0:18
 * @本类描述	  Fragment 管理类
 * @内容说明   1.单Activity继承BaseActivity
 *            2.单Activity+多Fragment用HomeActivity
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
    public    DrawerLayout                           mDrawerLayout;
    public    ActionBarDrawerToggle                  actionBarDrawerToggle;
    private   NavigationView                         mNavigationView;

    @Override

    protected int getContentViewId() {
        //主页面的布局
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_home);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        initToolBar();
        initNavigationView();


    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Title");
        mToolbar.setTitleTextColor(Color.rgb(0, 30, 0));
        mToolbar.setSubtitle("Subtitle_0.0");
        mToolbar.setSubtitleTextColor(Color.rgb(0, 30, 0));
    }


    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item1:
                        addFragment(R.id.fl_main, new FirstFragment());
                        break;
                    case R.id.nav_item2:
                        startActivity(new Intent(mContext, TwoActivity.class));
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    /**
     * 处理Fragment替换
     */
    @Override
    protected void initData() {
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment mainFragment = MainFragment.newInstance();
            if (null != mainFragment) {
                addFragment(R.id.fl_main, mainFragment);
            }
        }

    }

    /**
     * DrawerLayout 开关事件
     */
    @Override
    protected void initEvent() {
        //ActionBarDrawerToggle是DrawerLayout.DrawerListener的实现，可以方便的将drawlayout和actionbar结合起来

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R
                .string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };


        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        super.initEvent();


    }

    /**
     * @param layoutID 要替换的 FrameLayout
     * @param fragment
     */
    public void addFragment(int layoutID, BaseFragment fragment) {
        if (fragment != null) {
            //1.获取管理器
            fragmentManager = getSupportFragmentManager();
            //2.开始事务
            fragmentManager.beginTransaction()//
                    //3.操作事务
                    .replace(layoutID, fragment, fragment.getClass().getSimpleName())//参数(资源ID,fragment类,TAG)  TAG可以自定义
                    .addToBackStack(fragment.getClass().getSimpleName())//把Fragment添加到栈
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE) //设置跳转动画
                    //提交事务
                    .commitAllowingStateLoss();//
        }
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

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
