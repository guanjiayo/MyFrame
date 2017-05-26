package xmx.zs.baseframe.base;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/4/15
 * @本类描述
 * @内容说明   ${TODO} 这个Base类需要测试,测试完,本项目只保留一个
 *           //TODO 数组来说,LinkList 增删快,,效率更高
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment2 extends FragmentActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();


    public void addToFragmentList(Fragment fragment) {
        if (mFragmentList.contains(fragment)) {
            //先将老的Fragment移除
            mFragmentList.remove(fragment);
            //将Fragment添加到List的最后
            mFragmentList.add(fragment);
        } else {
            mFragmentList.add(fragment);
        }
    }

    /**
     * //TODO 在BaseFragmentActivity写一个,高版本的Activity底层好像就是FragmentActivity
     *
     * @param contentID
     * @param fragment
     * @param tag
     */
    public void addFragment(int contentID, Fragment fragment, String tag) {
        //1.获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2.fragmentManager获取事务管理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /**
         * 3.将Fragment添加到帧布局中
         * 调用改啊方法后,将不作为默认的,hide掉
         */

        transaction
                .add(contentID, fragment, tag)
                .commit();


    }

    /**
     * 第一次进入Fragment操作,在Activity实现这个方法
     */
    public void Frist(Fragment fragment) {
        //1.获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2.fragmentManager获取事务管理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏上面添加过的所有Fragment
        transaction.hide(fragment);
        //点击一个Fragment,就把该Fragment添加到我们的FragmentList
        addToFragmentList(fragment);


        //点击哪个Fragment,显示哪个
        //transaction.show(fragment);

    }

    /**
     * 监听返回键键,回退Fragment
     */
    @Override
    public void onBackPressed() {
        //两个或以上Fragment,才能回退
        if (mFragmentList.size() > 1) {
            //移除最顶端的Fragment
            mFragmentList.remove(mFragmentList.size()-1);
            //将上一个Fragment显示出来
            showFragment(mFragmentList.get(mFragmentList.size()-1));
        } else {
            //没有Fragment就退出Activity
            finish();
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.hide(fragment);  要先隐藏掉所有的,在添加
        transaction.show(fragment).commit();
    }
}
