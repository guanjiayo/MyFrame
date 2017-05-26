package xmx.zs.bjframe.Pages;

import android.view.Gravity;
import android.widget.TextView;

import xmx.zs.bjframe.base.BasePages;
import xmx.zs.bjframe.base.HomeActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/12 0:07
 * @本类描述	  设置Page
 * @内容说明  
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class SettingPage extends BasePages {

    public SettingPage(HomeActivity context) {
        super(context);
    }


    @Override
    public void initData() {
        fl_content.removeAllViews();
        //就是往 Fragmentlayout  加数据
        TextView tv_content = new TextView(mContext);
        tv_content.setText("设置的内容");
        tv_content.setTextSize(30);
        tv_content.setGravity(Gravity.CENTER);

        fl_content.addView(tv_content);
    }
}
