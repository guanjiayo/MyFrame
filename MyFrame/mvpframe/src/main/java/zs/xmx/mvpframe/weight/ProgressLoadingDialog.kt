package zs.xmx.mvpframe.weight

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import zs.xmx.mvpframe.R

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/24 0:46
 * @本类描述	  加载进度的Dialog
 * @内容说明
 *
 */

//改成私有构造,不让其他类以构造方式创建
class ProgressLoadingDialog private constructor(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    companion object {
        private lateinit var mDialog: ProgressLoadingDialog
        private var animDrawable: AnimationDrawable? = null

        fun create(context: Context): ProgressLoadingDialog {
            mDialog = ProgressLoadingDialog(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            //设置Dialog属性
            //dialog弹出后会点击屏幕或物理返回键,dialog消失
            mDialog.setCancelable(true)
            //dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
            mDialog.setCanceledOnTouchOutside(false)
            //dialog居中显示
            mDialog.window!!.attributes!!.gravity = Gravity.CENTER

            val lp = mDialog.window!!.attributes
            lp!!.dimAmount = 0.2f
            //设置属性
            mDialog.window!!.attributes = lp

            //获取动画视图
            val loadingView = mDialog.findViewById<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog


        }


    }

    /*
        显示加载对话框，动画开始
     */
    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    /*
        隐藏加载对话框，动画停止
     */
    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }


}