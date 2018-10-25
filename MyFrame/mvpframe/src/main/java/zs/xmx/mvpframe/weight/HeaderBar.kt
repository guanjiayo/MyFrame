package zs.xmx.mvpframe.weight

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_header_bar.view.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.ext.onClick

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/23 17:15
 * @本类描述	  自定义View实现通用Toolbar
 * @内容说明   后面在这个基础上扩展即可
 *
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    //是否显示"返回"图标
    private var isShowBack = true
    //Title文字
    private var titleText: String? = null
    //右侧文字
    private var rightText: String? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        //释放资源
        typedArray.recycle()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)

        //返回图标的显示状态
        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE

        //返回图标默认实现（关闭Activity）
        //这里使用了我们定义的Kotlin扩展
        mLeftIv.onClick {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }

        //标题不为空，设置值
        titleText?.let {
            mTitleTv.text = it
        }

        //右侧文字不为空，设置值
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

    }

    /*
        获取左侧视图
     */
    fun getLeftView(): ImageView {
        return mLeftIv
    }

    /*
        获取右侧视图
     */
    fun getRightView(): TextView {
        return mRightTv
    }

    /*
        获取右侧文字
     */
    fun getRightText(): String {
        return mRightTv.text.toString()
    }


}