package zs.xmx.permission;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import zs.xmx.permission.callback.IPermission;
import zs.xmx.permission.utils.PermissionUtils;

/**
 * 创建一个用户无感知的Activity,专门用于动态申请权限
 * 1.取消该Activity的转场动画
 */
public class PermissionRequestActivity extends AppCompatActivity {

    private static IPermission permissionListener;
    private String[] permissions;
    private static final String PERMISSION_KEY = "permission_key";
    private static final String REQUEST_CODE = "request_code";
    private int requestCode;

    /**
     * 跳转到我们用于申请权限的Activity
     *
     * @param context
     * @param permissions
     * @param requestCode
     * @param iPermission
     */
    public static void PermissionRequest(Context context, String[] permissions, int requestCode, IPermission iPermission) {
        permissionListener = iPermission;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        //独立的Activity栈专门处理权限
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION_KEY, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);

        //屏蔽进入Activity动画
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        /**获取传过去的bundle信息**/
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            permissions = bundle.getStringArray(PERMISSION_KEY);
            requestCode = bundle.getInt(REQUEST_CODE, 0);
        }
        if (permissions == null || permissions.length <= 0) {
            finish();
            return;
        }

        requestPermission(permissions);
    }

    /**
     * 申请权限
     *
     * @param permissions
     */
    private void requestPermission(String[] permissions) {
        //检查权限
        if (PermissionUtils.hasSelfPermissions(this, permissions)) {
            //权限已经被授权
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
                permissionListener = null;
            }
            finish();
            overridePendingTransition(0, 0);
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.verifyPermissions(grantResults)) {
            //申请的所有权限都通过
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
            }
        } else {
            //权限被拒绝,且选中不在提示
            if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
                if (permissions.length != grantResults.length) return;
                //讲被拒绝的权限保存到数组
                List<String> denyList = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == -1) {
                        denyList.add(permissions[i]);
                    }
                }
                if (permissionListener != null) {
                    permissionListener.PermissionDenied(denyList);
                }
            } else {
                //权限第一次被拒绝(权限被取消)
                if (permissionListener != null) {
                    permissionListener.PermissionCanceled();
                }
            }

        }
        permissionListener = null;
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void finish() {
        super.finish();
        //屏蔽Activity退出动画
        overridePendingTransition(0, 0);
    }
}
