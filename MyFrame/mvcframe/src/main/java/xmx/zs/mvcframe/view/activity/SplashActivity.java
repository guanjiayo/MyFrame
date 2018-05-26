package xmx.zs.mvcframe.view.activity;

import android.view.View;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.base.BaseActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/7 22:03
 * @本类描述	  启动页面
 * @内容说明
 *
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int setContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startActivity(LoginActivity.class);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {

    }
}
