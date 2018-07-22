package xmx.zs.mvcframe.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.base.BaseActivity;
import xmx.zs.mvcframe.isolation.normal.tencent.TencentRequest;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/19 0:20
 * @本类描述	  分享界面
 * @内容说明
 *
 */
public class ShareActivity extends BaseActivity {

    @Override
    protected int setContentViewId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_qq_share).setOnClickListener(this);
        findViewById(R.id.btn_wechat_share).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qq_share:
                TencentRequest.getInstance().shareUrlToQQ(this);
                break;
            case R.id.btn_wechat_share:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        TencentRequest.getInstance().onActivityResultData(requestCode, resultCode, data);
    }
}
