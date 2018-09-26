package zs.xmx.mvpframe.view.activity

import android.Manifest
import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.permission.annotation.NeedPermission
import zs.xmx.permission.annotation.PermissionCanceled
import zs.xmx.permission.annotation.PermissionDenied
import zs.xmx.permission.bean.DenyBean
import zs.xmx.permission.utils.SettingUtils


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/7 22:03
 * @本类描述	  启动页面
 * @内容说明
 *
 */
class SplashActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_splash
    }


    override fun initData() {
        permissionGranted()
    }

    override fun onClick(v: View) {

    }

    @NeedPermission(value = [Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.CALL_PHONE])
    fun permissionGranted() {
        Thread(Runnable {
            try {
                Thread.sleep(2000)
                startActivity(LoginActivity::class.java)
                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }

    /**
     * 权限被取消
     */
    @PermissionCanceled
    fun dealCancelPermission() {
        showToast("权限被取消")
    }

    /**
     * 权限被拒绝
     */
    @PermissionDenied
    fun dealPermission(bean: DenyBean) {
        val denyList = bean.denyList
        val sb = StringBuilder()
        for (s in denyList) {
            when (s) {
                Manifest.permission.ACCESS_FINE_LOCATION -> sb.append("定位,")
                Manifest.permission.CALL_PHONE -> sb.append("电话,")
                Manifest.permission.CAMERA -> sb.append("相机,")
            }
        }
        Toast.makeText(this, sb.toString() + "权限被拒绝:", Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(sb.toString() + "权限被禁止，需要手动打开")
                .setPositiveButton("去设置") { dialog, _ ->
                    dialog.dismiss()
                    SettingUtils.go2Setting(this@SplashActivity)
                }
                .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
                .create().show()
    }

}
