package xmx.zs.baseframe.fragment;

import android.view.View;

import xmx.zs.baseframe.R;
import xmx.zs.baseframe.base.BaseFragment;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/9 17:22
 * @本类描述	  MainFragment
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class MainFragment extends BaseFragment {


    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_main, null);
        return rootView;
    }

    /**
     * 类似构造函数,谁调用,谁实例化
     * <p>
     * 同时也可以通过这个方法传递参数
     *
     * @return
     */
    public static MainFragment newInstance() {
        return new MainFragment();

    }
}
