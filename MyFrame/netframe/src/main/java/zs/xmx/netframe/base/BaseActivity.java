package zs.xmx.netframe.base;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import zs.xmx.netframe.receiver.NetWorkReceiver;
import zs.xmx.netframe.utils.Logger;
import zs.xmx.netframe.utils.NetWorkUtils;
import zs.xmx.netframe.utils.ToastUtils;

/*
 * @创建者     mqm
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/5/17
 * @本类描述	  BaseActivity
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */

public abstract class BaseActivity extends RxAppCompatActivity implements NetWorkReceiver.onNetWorkStataChangedListener {
    private NetWorkReceiver     mMyReceiver; //网络状态广播
    private AlertDialog.Builder mBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        Logger.isDebug = true;
        registerReceiver();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(BaseActivity.this, msg);
    }

    /**
     * 注册广播
     */
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mMyReceiver = new NetWorkReceiver();
        mMyReceiver.setOnNetWorkStataChangedListener(this);
        this.registerReceiver(mMyReceiver, intentFilter);
    }

    /**
     * 监听全局网络状态
     *
     * @param mNetWorkTypeName
     */
    @Override
    public void onNetWorkStataChanged(String mNetWorkTypeName) {
        if (mNetWorkTypeName.equals("NETWORK_UNKNOWN") || mNetWorkTypeName.equals("NETWORK_NO")) { //没网
            showDialog();
        } else { //有网
            if (!NetWorkUtils.isNetworkOnline()) {//判断数据连通状态
                showDialog();
            }
        }
    }


    private void showDialog() {
        if (mBuilder == null) {
            //显示Diag
            mBuilder = new AlertDialog.Builder(this);
        }
        mBuilder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                NetWorkUtils.openWirelessSettings(getApplicationContext());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(mMyReceiver);
    }
}
