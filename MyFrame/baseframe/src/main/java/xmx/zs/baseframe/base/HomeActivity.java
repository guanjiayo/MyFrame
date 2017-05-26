package xmx.zs.baseframe.base;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import xmx.zs.baseframe.R;
import xmx.zs.baseframe.fragment.MainFragment;
import xmx.zs.baseframe.utils.ActivityManager;


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
 * @新增内容 //TODO 写到最外层,叫MainActivity
 *
 */
public class HomeActivity extends BaseActivity {
    //点击时间
    private   long                                   mPreClickTime;
    protected android.support.v4.app.FragmentManager fragmentManager;
    private   Toolbar                                mToolbar;


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


    }

    /**
     * 处理Fragment替换
     * <p>
     * addFragment(...)
     */
    @Override
    protected void initData() {

        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment mainFragment = MainFragment.newInstance();
            if (mainFragment!=null) {
                addFragment(R.id.fl_main, mainFragment);
            }
        }


    }

    /**
     * DrawerLayout 开关事件
     */
    @Override
    protected void initEvent() {
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

   /* */

    /**
     * 如果同时要显示两种Fragment 用这个
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

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
