package zs.xmx.mvpframe.utils.factory

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


import zs.xmx.mimarket.view.fragment.*
import zs.xmx.mvpframe.base.BaseFragment
import zs.xmx.mvpframe.view.fragment.*

object FragmentFactory {
    private var mMainFragment: MainFragment? = null
    private var mTwoFragment: TwoFragment? = null
    private var mThreeFragment: ThreeFragment? = null
    private var mFourFragment: FourFragment? = null
    private var mFiverFragment: FiverFragment? = null

    /**
     * 简单工厂创建Fragment,可以再扩展一个反射实现的简单工厂
     *
     * @param position
     * @return
     */
    fun createFragment(position: Int): BaseFragment? {
        var mBaseFragment: BaseFragment? = null
        when (position) {
            0 -> {
                if (mMainFragment == null) {
                    mMainFragment = MainFragment()
                }
                mBaseFragment = mMainFragment
            }
            1 -> {
                if (mTwoFragment == null) {
                    mTwoFragment = TwoFragment()
                }
                mBaseFragment = mTwoFragment
            }
            2 -> {
                if (mThreeFragment == null) {
                    mThreeFragment = ThreeFragment()
                }
                mBaseFragment = mThreeFragment
            }
            3 -> {
                if (mFourFragment == null) {
                    mFourFragment = FourFragment()
                }
                mBaseFragment = mFourFragment
            }
            4 -> {
                if (mFiverFragment == null) {
                    mFiverFragment = FiverFragment()
                }
                mBaseFragment = mFiverFragment
            }
        }

        return mBaseFragment
    }

}
