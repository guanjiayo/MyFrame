package zs.xmx.mvpframe.view.activity

import android.view.View

import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/7 22:03
 * @本类描述	  启动页面
 * @内容说明
 *
 */
class SplashActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {

    }

    override fun initEvent() {
        Thread(Runnable {
            try {
                Thread.sleep(2000)
                startActivity(LoginActivity::class.java)
                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }

    override fun onClick(v: View) {

    }

}
