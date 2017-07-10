package zs.xmx.netframe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import zs.xmx.netframe.utils.NetWorkUtils;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/16
 * @本类描述	  监听手机网络状态广播
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public class NetWorkReceiver extends BroadcastReceiver {

    private String mNetWorkTypeName;


    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            mNetWorkTypeName = NetWorkUtils.getNetWorkTypeName(context);
            if (mOnNetWorkStataChangedListener != null) {
                mOnNetWorkStataChangedListener.onNetWorkStataChanged(mNetWorkTypeName);
            }

        }
    }

    public interface onNetWorkStataChangedListener {
        void onNetWorkStataChanged(String mNetWorkTypeName);
    }

    private onNetWorkStataChangedListener mOnNetWorkStataChangedListener;

    public void setOnNetWorkStataChangedListener(onNetWorkStataChangedListener listener) {
        this.mOnNetWorkStataChangedListener = listener;
    }
}
