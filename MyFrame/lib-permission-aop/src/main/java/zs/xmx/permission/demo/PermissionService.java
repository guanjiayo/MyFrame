package zs.xmx.permission.demo;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import zs.xmx.permission.annotation.NeedPermission;
import zs.xmx.permission.annotation.PermissionCanceled;
import zs.xmx.permission.annotation.PermissionDenied;
import zs.xmx.permission.bean.DenyBean;
import zs.xmx.permission.utils.SettingUtils;


public class PermissionService extends Service {

    public PermissionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestCamera();
            }
        }, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    @NeedPermission(value = {Manifest.permission.READ_CONTACTS})
    private void requestCamera() {
        Toast.makeText(PermissionService.this, "读取联系人权限已经被同意", Toast.LENGTH_SHORT).show();
    }


    @PermissionDenied
    public void deniedCallBack(DenyBean bean) {
        Toast.makeText(PermissionService.this, "读取联系人已经被禁止", Toast.LENGTH_SHORT).show();
        SettingUtils.go2Setting(PermissionService.this);
    }

    @PermissionCanceled
    public void canceledCallBack() {
        Toast.makeText(PermissionService.this, "读取联系人已经被取消", Toast.LENGTH_SHORT).show();
    }
}
