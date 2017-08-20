package xmx.zs.baseframe.utils;
/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/3/4 12:31
 * @本类描述	  Fragment工厂工具类
 * @内容说明   工厂设计模式+单例设计模式创建Fragment
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间
 * @新增内容
 *
 */


import xmx.zs.baseframe.base.BaseFragment;
import xmx.zs.baseframe.view.fragment.MainFragment;

public class FragmentFactory {
    private static MainFragment mMainFragment;


    public static BaseFragment getFragment(int position) {
        BaseFragment mBaseFragment = null;
        switch (position) {
            case 0:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                }
                mBaseFragment = mMainFragment;
                break;
            case 1:
                //
                break;
            case 2:
                //
                break;
        }
        return mBaseFragment;
    }

}
