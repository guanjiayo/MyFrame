package zs.xmx.mvpframe.view.activity

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.mvpframe.base.HomeActivity
import zs.xmx.mvpframe.bus.rx_todo.RxBus_todo
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.isolation.normal.tencent.TencentRequest
import zs.xmx.mvpframe.isolation.normal.wechat.WeChatRequest


class LoginActivity : BaseActivity() {


    override fun setContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initEvent() {

        //RxBus绑定BasePresenter
        RxBus_todo.getDefault().register(mLoginPresenter)

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
                mLoginPresenter.loadDataFromNet(MyConstant.LOGIN, params)
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


    override fun urlRequestSuccess(result: Any) {
        startActivity(HomeActivity::class.java)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TencentRequest.getInstance().onActivityResultData(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        //RXBus反注册
        RxBus_todo.getDefault().unRegister(mLoginPresenter)
    }
}
