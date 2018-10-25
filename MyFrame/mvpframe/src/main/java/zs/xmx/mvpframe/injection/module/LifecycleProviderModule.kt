package zs.xmx.mvpframe.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/23 3:52
 * @本类描述	  RxLifecycle 生命周期第三方库的Module
 * @内容说明   用于将第三方库对象注入
 *
 */
/**
 * @param lifecycleProvider
 *<p>
 * 由Component 和 Module 绑定那个类传进来
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {
    @Provides
    fun provideLifecycleProvider(): LifecycleProvider<*> {
        return this.lifecycleProvider
    }
}