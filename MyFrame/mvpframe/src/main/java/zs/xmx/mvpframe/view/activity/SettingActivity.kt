package zs.xmx.mvpframe.view.activity

import android.view.View
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.base.BaseActivity
import zs.xmx.mvpframe.view.fragment.NormalPreferenceFragment

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/8 22:12
 * @本类描述	  设置界面
 * @内容说明   todo https://www.cnblogs.com/tianzhijiexian/p/3893504.html 写一个完全自定义的Preference
 *
 */
class SettingActivity : BaseActivity() {

    override fun onClick(v: View?) {
    }

    override fun initView() {
        val fragmentManager = fragmentManager
        val transaction = fragmentManager.beginTransaction().replace(R.id.fl_content, NormalPreferenceFragment())
        transaction.commit()
    }
    override fun setContentViewId(): Int {
        return R.layout.activity_setting
    }
}