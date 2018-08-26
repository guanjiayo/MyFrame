package zs.xmx.mvpframe.view.activity

import android.content.Intent
import android.view.View

import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.mvpframe.isolation.normal.tencent.TencentRequest
import zs.xmx.mvpframe.isolation.normal.wechat.WeChatRequest


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/19 0:20
 * @本类描述	  分享界面
 * @内容说明
 *
 */
class ShareActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_share
    }

    override fun initEvent() {
        findViewById<View>(R.id.btn_qq_share).setOnClickListener(this)
        findViewById<View>(R.id.btn_wechat_share).setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_qq_share -> TencentRequest.getInstance().shareUrlToQQ(this)
            R.id.btn_wechat_share -> WeChatRequest.getInstance().pay(this, "")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TencentRequest.getInstance().onActivityResultData(requestCode, resultCode, data)
    }


}
