package zs.xmx.permission.support;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;


import zs.xmx.permission.callback.ISetting;


public class VIVO implements ISetting {

    private Context context;

    public VIVO(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        if (appIntent != null && Build.VERSION.SDK_INT < 23) {
            context.startActivity(appIntent);
            return null;
        }
        Intent vIntent = new Intent();
        vIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        vIntent.setAction(Settings.ACTION_SETTINGS);
        return vIntent;
    }
}
