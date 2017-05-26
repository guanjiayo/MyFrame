package xmx.zs.newsframe.fragment;

import android.view.View;

import xmx.zs.newsframe.R;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/7 16:17
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class FirstFragment extends BaseFragment {
    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_first, null);
        return rootView;
    }
}
