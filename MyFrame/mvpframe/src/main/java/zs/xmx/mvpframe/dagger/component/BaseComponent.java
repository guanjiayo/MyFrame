package zs.xmx.mvpframe.dagger.component;

import dagger.Component;
import zs.xmx.mvpframe.base.BaseActivity;
import zs.xmx.mvpframe.dagger.module.BasePresenterModule;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/25 17:07
 * @本类描述	  Activity Component基类
 * @内容说明  这里对应所有继承了BasesActivity的Component
 *
 */
@Component(modules = BasePresenterModule.class)
public interface BaseComponent {
    void inject(BaseActivity baseAct);
}
