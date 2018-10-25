package zs.xmx.mvpframe.ui.activity

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.activity.BaseMvpActivity
import zs.xmx.mvpframe.base.activity.HomeActivity
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.injection.component.DaggerUserComponent
import zs.xmx.mvpframe.injection.module.UserModule
import zs.xmx.mvpframe.isolation.normal.tencent.TencentRequest
import zs.xmx.mvpframe.isolation.normal.wechat.WeChatRequest
import zs.xmx.mvpframe.presenter.LoginPresenter
import zs.xmx.mvpframe.view.impl.activity.ILoginView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/18 21:39
 * @本类描述   登录页面
 * @内容说明
 *
 *
 */
/**
 * 这里形式实现登录结果接口数据回调,以及当前类真实实现的presenter传回去BaseMvpActivity
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), ILoginView {

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }


    override fun setContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initEvent() {
        btn_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        btn_qq_login.setOnClickListener(this)
        btn_wechat_login.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {//普通登录
                val params = HashMap<String, Any>()
                params["key"] = MyConstant.MOB_KEY
                params["username"] = et_username.text.toString()
                params["password"] = et_pwd.text.toString()
                mPresenter.login(MyConstant.LOGIN, params, TAG)
            }

            R.id.btn_wechat_login -> {//微信登录
                WeChatRequest.getInstance().login(this)
            }

            R.id.btn_qq_login -> {//QQ登录
                showToast("SSO登录:" + TencentRequest.getInstance().isSupportSSOLogin(this))
                TencentRequest.getInstance().login(this)
            }
            R.id.btn_register -> {//注册
                startActivity(RegisterActivity::class.java)
            }
        }


    }


    override fun onLoginResult(result: Any) {
        showToast(result.toString())
        startActivity(HomeActivity::class.java)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TencentRequest.getInstance().onActivityResultData(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        //RXBus反注册
        mPresenter.unRegisterBus()
    }
}
