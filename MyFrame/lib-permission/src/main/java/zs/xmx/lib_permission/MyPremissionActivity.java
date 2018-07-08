package zs.xmx.lib_permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import zs.xmx.lib_permission.core.IPermission;

/**
 * 使用:
 *
 * @Permission(Manifest.permission.CAMERA) private void requestLocation() {
 * //doSomething
 * }
 * @PermissionDenied() private void deny() {
 * //doSomething
 * }
 * @PermissionCanceled() private void cancel() {
 * //doSomething
 * }
 */

/**
 * 用户无感知的Activity,用于切入后真正实现申请权限的类
 */
public class MyPremissionActivity extends AppCompatActivity {

    private static final String PARAM_PERMISSION   = "param_permission";
    private static final String PARAM_REQUEST_CODE = "param_request_code";

    private static IPermission permissionListener;
    private        String[]    mPermissions;
    private        int         mRequestCode;

    /**
     * 请求权限
     */
    public static void requestPermission(Context context, String[] permissions, int requestCode, IPermission iPermission) {

        permissionListener = iPermission;
        Intent intent = new Intent(context, MyPremissionActivity.class);
        //把我们当前的Activity放到一个独立的栈中,以实现用户无感知
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PARAM_PERMISSION, permissions);
        bundle.putInt(PARAM_REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            //屏蔽进入Activity的场动画
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_premission);

        this.mPermissions = getIntent().getStringArrayExtra(PARAM_PERMISSION);
        this.mRequestCode = getIntent().getIntExtra(PARAM_REQUEST_CODE, -1);

        if (mPermissions == null || mRequestCode < 0 || permissionListener == null) {
            this.finish();
            return;
        }
        //检查是否已授权
        if (PermissionUtils.hasPermission(this, mPermissions)) {
            permissionListener.ganted();
            finish();
            return;
        }
        // 权限申请
        ActivityCompat.requestPermissions(this, this.mPermissions, this.mRequestCode);
    }

    /**
     * 权限申请后的回调状态
     * <p>
     * grantResults对应于申请的结果，这里的数组对应于申请时的第二个权限字符串数组。
     * 如果你同时申请两个权限，那么grantResults的length就为2，分别记录你两个权限的申请结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //请求权限成功
        if (PermissionUtils.verifyPermission(this, grantResults)) {
            permissionListener.ganted();
            finish();
            return;
        }

        //用户点击了不再显示,提示UI,然后跳转到设置页面
        if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
            //            if (permissions.length != grantResults.length) {
            //                return;
            //            }
            //            List<String> deinedList = new ArrayList<>();
            //            for (int i = 0; i < grantResults.length; i++) {
            //                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
            //                    deinedList.add(permissions[i]);
            //                }
            //            }
            permissionListener.denied();
            finish();
            return;
        }

        //用户取消
        permissionListener.cancled();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        //屏蔽Activity退出动画
        overridePendingTransition(0, 0);
    }
}
