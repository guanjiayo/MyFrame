package zs.xmx.mvpframe.view.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.mvpframe.bus.rx_todo.RxBus_todo
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.utils.Logger
import java.util.*

class RegisterActivity : BaseActivity() {


    override fun setContentViewId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        findViewById<View>(R.id.btn_regiest).setOnClickListener(this)
    }

    override fun initEvent() {
        //RxBus绑定BasePresenter
        RxBus_todo.getDefault().register(mRegisterPresenter)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_regiest -> {
                val params = HashMap<String, Any>()
                params["key"] = MyConstant.MOB_KEY
                params["username"] = et_userName.text.toString()
                params["password"] = et_pwd.text.toString()
                mRegisterPresenter.loadDataFromNet(MyConstant.REGIESTER, params)
            }
        }
    }


    override fun urlRequestSuccess(result: Any) {
        Logger.e(TAG, result.toString())
        showToast(result.toString())

    }

    override fun onDestroy() {
        super.onDestroy()
        //RxBus 反注册
        RxBus_todo.getDefault().unRegister(mRegisterPresenter)
    }

}
