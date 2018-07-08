package zs.xmx.permission.support;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import zs.xmx.permission.BuildConfig;
import zs.xmx.permission.callback.ISetting;

public class Sony implements ISetting {

    private Context context;

    public Sony(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
        intent.setComponent(comp);
        return intent;
    }
}