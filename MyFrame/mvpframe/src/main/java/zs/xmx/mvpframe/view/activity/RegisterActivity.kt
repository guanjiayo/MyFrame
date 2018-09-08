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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_regiest -> {
                //todo 传参
                val params = HashMap<String, Any>()
                params["key"] = MyConstant.MOB_KEY
                params["username"] = et_userName.text.toString()
                params["password"] = et_pwd.text.toString()
                mRegisterPresenter.loadDataFromNet(params)
            }
        }
    }

    override fun initEvent() {
        //new RegisterPresenter(this).fetch();
        //RxBus注册(类似EventBus)
        //RxBus_todo.getDefault().register(mRegisterPresenter)
        RxBus_todo.getDefault().register(mRegisterPresenter)
        //mPresenter = new RegisterPresenter(this);

    }


    override fun onDestroy() {
        super.onDestroy()
        //RxBus取消注册(类似EventBus)
        //RxBus_todo.getDefault().unRegister(mRegisterPresenter)
        RxBus_todo.getDefault().unRegister(mRegisterPresenter)
    }


    override fun urlRequestSuccess(result: Any) {
        Logger.e(TAG, result.toString())
        showToast(result.toString())

    }

}
