package zs.xmx.mvpframe.weight

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.Button
import zs.xmx.mvpframe.R

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/23 17:57
 * @本类描述	  带倒计时的 "获取验证码" Button
 * @内容说明   自定义View
 *
 * todo 改成一下,使用安卓原生倒计时器
 */
class VerifyButton(context: Context?, attrs: AttributeSet?) : Button(context, attrs) {
    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: OnVerifyBtnClick? = null

    init {
        this.text = "获取验证码"
        mHandler = Handler()

    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
        if (mOnVerifyBtnClick != null) {
            mOnVerifyBtnClick!!.onClick()
        }

    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        override fun run() {
            this@VerifyButton.text = mCount.toString() + "s "
            this@VerifyButton.setBackgroundColor(resources.getColor(R.color.common_disable))
            this@VerifyButton.setTextColor(resources.getColor(R.color.common_white))
            this@VerifyButton.isEnabled = false

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    /*
        恢复到初始状态
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(resources.getColor(R.color.transparent))
        this.setTextColor(resources.getColor(R.color.common_blue))
        mCount = 60
    }

    /*
        点击事件接口
     */
    interface OnVerifyBtnClick {
        fun onClick()
    }

    fun setOnVerifyBtnClick(onVerifyBtnClick: OnVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}