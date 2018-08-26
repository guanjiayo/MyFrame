package zs.xmx.mvpframe.view.fragment;

import android.view.View;
import android.widget.TextView;

import zs.xmx.mvpframe.R;
import zs.xmx.mvpframe.base.BaseFragment;


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
    protected int setLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText("MainFragment");
    }


}
