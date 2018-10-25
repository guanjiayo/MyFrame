package zs.xmx.mvpframe.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.activity.BaseMvpActivity
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.ext.onClick
import zs.xmx.mvpframe.injection.component.DaggerUserComponent
import zs.xmx.mvpframe.injection.module.UserModule
import zs.xmx.mvpframe.presenter.RegisterPresenter
import zs.xmx.mvpframe.utils.Logger
import zs.xmx.mvpframe.view.impl.activity.IRegisterView
import java.util.*

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), IRegisterView {

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }


    override fun setContentViewId(): Int {
        return R.layout.activity_register
    }


    override fun initEvent() {
        btn_regiest.onClick(this)
        mVerifyCodeBtn.onClick(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_regiest -> {
                val params = HashMap<String, Any>()
                params["key"] = MyConstant.MOB_KEY
                params["username"] = et_userName.text.toString()
                params["password"] = et_pwd.text.toString()
                mPresenter.register(MyConstant.REGISTER, params, TAG)
            }
            R.id.mVerifyCodeBtn->{
                mVerifyCodeBtn.requestSendVerifyNumber()
            }
        }
    }


    override fun onRegisterResult(result: Any) {
        Logger.e(TAG, result.toString())
        showToast(result.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        //RXBus反注册
        mPresenter.unRegisterBus()
    }


}
