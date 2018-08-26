package zs.xmx.mvpframe.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

import zs.xmx.mvpframe.utils.NetWorkUtils


/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/16
 * @本类描述	  监听手机网络状态广播
 * @内容说明  当手机状态发生改变,将数据用接口回调方式,传递出去
 * @补充内容
 *
 * ---------------------------------
 * @更新时间
 * @新增内容
 *
 */

class NetWorkReceiver : BroadcastReceiver() {

    private var mOnNetWorkStateChangedListener: onNetWorkStateChangedListener? = null


    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            //获取网络状态
            val netWorkTypeName = NetWorkUtils.getNetWorkTypeName(context)
            if (mOnNetWorkStateChangedListener != null) {

                mOnNetWorkStateChangedListener!!.onNetWorkStateChanged(netWorkTypeName)
            }

        }
    }

    interface onNetWorkStateChangedListener {
        fun onNetWorkStateChanged(mNetWorkTypeName: String)
    }

    fun setOnNetWorkStateChangedListener(listener: onNetWorkStateChangedListener) {
        this.mOnNetWorkStateChangedListener = listener
    }
}
