package zs.xmx.permission.support;


import android.content.Context;
import android.content.Intent;

import zs.xmx.permission.BuildConfig;
import zs.xmx.permission.callback.ISetting;


public class MeiZu implements ISetting {

    private Context context;

    public MeiZu(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        return intent;
    }
}