package xmx.zs.baseframe.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xmx.zs.baseframe.R;
import xmx.zs.baseframe.base.HomeActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/11
 * @本类描述	  偏好设置Fragment
 * @内容说明   ${TODO} 这里看文章想想怎么搞
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class SettingFragment extends PreferenceFragment {
    protected HomeActivity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取上下文
        mContext = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(mContext, R.layout.fragment_setting, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
