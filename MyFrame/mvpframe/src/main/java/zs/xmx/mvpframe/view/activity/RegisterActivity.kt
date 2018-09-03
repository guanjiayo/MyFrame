package zs.xmx.mvpframe.view.activity

import android.view.View
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.mvpframe.net.retrofit_rx.databus.RxBus
import zs.xmx.mvpframe.utils.Logger
import java.util.*

class RegisterActivity : BaseActivity() {


    override fun setContentViewId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        findViewById<View>(R.id.button).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
                //todo 传参
                val params = HashMap<String, Any>()
                params["category"] = "android"
                mRegisterPresenter.loadDataFromNet(params)
            }
        }
    }

    override fun initEvent() {
        //new RegisterPresenter(this).fetch();
        //RxBus注册(类似EventBus)
        RxBus.getInstance().register(mRegisterPresenter)
        //mPresenter = new RegisterPresenter(this);

    }


    override fun onDestroy() {
        super.onDestroy()
        //RxBus取消注册(类似EventBus)
        RxBus.getInstance().unRegister(mRegisterPresenter)
    }



    override fun urlRequestSuccess(result: Any) {
        Logger.e(TAG, result.toString())
        showToast(result.toString())

    }

}
