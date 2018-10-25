package zs.xmx.permission.demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import zs.xmx.permission.R;
import zs.xmx.permission.annotation.NeedPermission;
import zs.xmx.permission.annotation.PermissionCanceled;
import zs.xmx.permission.annotation.PermissionDenied;
import zs.xmx.permission.bean.DenyBean;
import zs.xmx.permission.utils.SettingUtils;

/**
 * 准备工作:(配置aspectjx)
 * 1.在Project配置classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.1'
 * 2.在app的moudle配置apply plugin: 'android-aspectjx'
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, new PermissionFragment());
        transaction.commitAllowingStateLoss();
    }

    private void initEvent() {
        findViewById(R.id.btn1_act).setOnClickListener(this);
        findViewById(R.id.btn2_act).setOnClickListener(this);
        findViewById(R.id.btn_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn1_act) {
            callMap();

        } else if (i == R.id.btn2_act) {
            callPhone();

        } else if (i == R.id.btn_service) {
            Intent intent = new Intent(this, PermissionService.class);
            startService(intent);

        }
    }

    /**
     * 申请多个权限
     */
    @NeedPermission(value = {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, requestCode = 200)
    private void callPhone() {
        Toast.makeText(this, "电话,相机权限通过", Toast.LENGTH_SHORT).show();
    }

    /**
     * 申请单个权限
     */
    @NeedPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void callMap() {
        Toast.makeText(this, "定位权限通过", Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限被取消
     */
    @PermissionCanceled
    public void dealCancelPermission() {
        Toast.makeText(this, "权限被取消", Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限被拒绝
     */
    @PermissionDenied
    public void dealPermission(DenyBean bean) {
        List<String> denyList = bean.getDenyList();
        StringBuilder sb = new StringBuilder();
        for (String s : denyList) {
            if (Manifest.permission.ACCESS_FINE_LOCATION.equals(s)) {
                sb.append("定位,");
            } else if (Manifest.permission.CALL_PHONE.equals(s)) {
                sb.append("电话,");
            } else if (Manifest.permission.CAMERA.equals(s)) {
                sb.append("相机,");
            }
        }
        Toast.makeText(this, sb.toString() + "权限被拒绝:", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(sb.toString() + "权限被禁止，需要手动打开")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SettingUtils.go2Setting(MainActivity.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
}
