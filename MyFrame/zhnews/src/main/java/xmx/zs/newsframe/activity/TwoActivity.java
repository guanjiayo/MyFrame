package xmx.zs.newsframe.activity;

import xmx.zs.newsframe.R;
import xmx.zs.newsframe.activity.base.BaseActivity;
import xmx.zs.newsframe.utils.ActivityManager;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/7 17:44
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class TwoActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_two;
    }

    @Override
    protected void initView() {
        ActivityManager.getInstance().addActivity(TwoActivity.this);
        showToast(ActivityManager.activityStack.lastElement().toString());
    }

    @Override
    protected void initData() {

    }
}
