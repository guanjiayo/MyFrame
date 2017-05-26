package xmx.zs.bjframe.activity;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import xmx.zs.bjframe.R;
import xmx.zs.bjframe.base.BaseActivity;
import xmx.zs.bjframe.base.HomeActivity;
import xmx.zs.bjframe.constant.MyConstant;
import xmx.zs.bjframe.utils.SPUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/9 20:51
 * @本类描述	  SplashActivity
 * @内容说明   旋转渐变动画
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class SplashActivity extends BaseActivity {
    private ImageView    iv_bg;
    private AnimationSet as;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        initAnimation();
    }

    /**
     * 动画
     */
    private void initAnimation() {
        as = new AnimationSet(false);
        // 旋转
        RotateAnimation ra = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        ra.setDuration(2000);
        ra.setFillAfter(true);
        // 缩放
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);
        // 渐变
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);

        as.addAnimation(aa);
        as.addAnimation(sa);
        as.addAnimation(ra);

        iv_bg.setAnimation(as);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        as.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // 动画结束 没有设置 进入向导界面 否则进入主界面
                boolean isSetupFinish = (boolean) SPUtils.getParam(MyConstant.ISSETUPFINISH, false);
                if (isSetupFinish) {

                    //进入主界面
                    Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                } else {
                    //进入设置向导页面
                    Intent guid = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(guid);
                    finish();
                }

            }
        });
        super.initEvent();
    }
}
