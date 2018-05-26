package xmx.zs.mvcframe.base;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.receiver.NetWorkReceiver;
import xmx.zs.mvcframe.utils.ActivityUtils;
import xmx.zs.mvcframe.utils.Logger;
import xmx.zs.mvcframe.utils.StatusBar;
import xmx.zs.mvcframe.utils.ToastUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/10/28 16:57
 * @本类描述	  基类Activity
 * @内容说明   1.单Activity继承BaseActivity
 *            2.单Activity+多Fragment用HomeActivity
 *
 * @一般要写   1.ToorBar
 *            2.颜色统一
 *            3.统一配置(屏幕,状态栏)
 *            4.Fragment管理
 *            5.Toast,dialog,progress,snakeBar写在这里,其他类调用更方便
 *            6.定义TAG,后面继承的Activity能直接使用
 *            7.第三方库(友盟统计使用,极光推送,百度地图...)
 *            8.生命周期的log/管理
 *            9.软键盘管理
 *            10.网络监听广播注册
 *            11.定义局部上下文(Activity Context)
 *            12.activity的入栈出栈操作,在这里调用ActManager TODO 删掉ActManager用Utils的方式
 *            13.请求网络框架,回调方法  参考RootActivity
 *
 *
 * ---------------------------------
 * @新增内容  //TODO 加上设置页面(清楚缓存,夜间模式之类)
 *           //TODO 加上登陆注册页面
 *
 * @补充内容  看后期新增的内容,部分方法可用接口定义出去
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements NetWorkReceiver.onNetWorkStataChangedListener, View.OnClickListener {
    //TAG 这里定义TAG,后面继承的Activity能直接使用
    protected final String TAG = BaseActivity.this.getClass().getSimpleName();
    //上下文
    protected Context         mContext;
    //点击时间
    private   long            mPreClickTime;
    //网络监听广播
    private   NetWorkReceiver mMyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setBaseConfig();
        setContentView(setContentViewId());
        /**如果这里使用了ButterKnife,子类不需要重写initView()**/
        //ButterKnife.bind(this);
        setScreen();
        mContext = BaseActivity.this;
        StatusBar.setStatusBarColor(this, Color.BLUE);
        initData();
        initView();
        initEvent();
        registerReceiver();
    }

    /**
     * 代码注册广播
     */
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mMyReceiver = new NetWorkReceiver();
        mMyReceiver.setOnNetWorkStataChangedListener(this);//绑定广播
        this.registerReceiver(mMyReceiver, intentFilter);
    }

    /**
     * 设置一些基本属性
     */
    private void setBaseConfig() {
        //ScreenUtils.setPortrait(this);//这里先设置为竖屏
        // ScreenUtils.setFullScreen(this);
        Logger.isDebug = true;

    }

    /**
     * 在这里设置屏幕方向,可让子类覆写
     */
    protected void setScreen() {
        // StatusBar.setTransparentStatusBar(this);
        //StatusBar.setStatusBarColor(this, Color.BLUE);
        showToast(this.getClass().getSimpleName() + "");
    }

    /**
     * 布局文件ID
     */
    protected abstract int setContentViewId();

    /**
     * 让子类覆盖此方法来完成页面的初始化
     * <p>
     * 初始化布局,界面方法
     */
    protected void initView() {

    }

    /**
     * 让子类覆盖此方法来完成数据的初始化
     */
    protected abstract void initData();

    /**
     * 绑定要设置点击事件的组件,让子类覆盖此方法来完成事件的处理
     * <p>
     * 如果使用了View注解框架,可以不重写
     */
    protected void initEvent() {

    }

    /**
     * 让子类覆盖此方法来完成事件的处理
     */
    public abstract void onClick(View v);

    protected void startActivity(Class<?> activity) {
        ActivityUtils.startActivity(activity);
    }

    protected void startActivity(Bundle bundle, Class<?> activity) {
        ActivityUtils.startActivity(bundle, activity);
    }

    /**
     * 吐司,在基类写,方便后面子类调用
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }

    /**
     * 网络监听广播回调
     *
     * @param mNetWorkTypeName 网络状态
     */
    @Override
    public void onNetWorkStataChanged(String mNetWorkTypeName) {
        showToast("当前网络类型" + mNetWorkTypeName);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播
        unregisterReceiver(mMyReceiver);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 可以在这里做判断token的有效期是否过期
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 增强用户体验
     * <p>
     * 在MainActivity主页,点击两次才能退出程序
     */
    @Override
    public void onBackPressed() {
        //TODO 要改成Fragment依赖的Activity
        if (this instanceof HomeActivity) {// 主页
            if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的间隔大于2s中
                showToast("再按一次,退出APP");
                mPreClickTime = System.currentTimeMillis();
                return;
            } else {
                ActivityUtils.AppExit(this);
            }
        } else {
            super.onBackPressed();// finish
        }


    }
    /**
     * 如果同时要显示两种Fragment 用这个(类似SlideMenu 类型)
     *//*
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 2) {
            //TODO 要改成Fragment依赖的Activity
            if (this instanceof HomeActivity) {
                if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的间隔大于2s中
                    showToast("再按一次,退出APP");
                    mPreClickTime = System.currentTimeMillis();
                    return;
                } else {

                    ActListManager.getInstance().AppExit(this);
                }
            }

        } else {
            removeFragment("LeftFragment");
        }

    }*/

}