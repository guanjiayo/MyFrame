package zs.xmx.permission.support;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import zs.xmx.permission.BuildConfig;
import zs.xmx.permission.callback.ISetting;

public class QiHu implements ISetting {

    private Context context;

    public QiHu(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        ComponentName comp = new ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        intent.setComponent(comp);
        return intent;
    }
}