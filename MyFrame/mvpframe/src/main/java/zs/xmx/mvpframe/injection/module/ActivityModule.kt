package zs.xmx.mvpframe.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import zs.xmx.mvpframe.injection.scope.ActivityScope

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 17:10
 * @本类描述	  Activity级别Module
 * @内容说明
 *
 */
/**
 * @param activity
 * 让注入的全局上下文,由继承于BaseMvpActivity的子类传进来
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }

}


