package zs.xmx.dagger2.component;

import dagger.Component;
import zs.xmx.dagger2.module.RegisterPresenterModule;
import zs.xmx.view.activity.RegisterActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/15 23:54
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 *
 */
@Component(modules = RegisterPresenterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity registerActivity);
}
