package zs.xmx.dagger2.module;

import dagger.Module;
import dagger.Provides;
import zs.xmx.presenter.RegisterPresenter;
import zs.xmx.view.activity.RegisterActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/15 23:50
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 *
 */
@Module
public class RegisterPresenterModule {
    RegisterActivity mRegisterActivity;

    public RegisterPresenterModule(RegisterActivity registerActivity) {
        this.mRegisterActivity = registerActivity;
    }

    @Provides
    public RegisterPresenter provideRegisterPresenter() {
        return new RegisterPresenter(mRegisterActivity);
    }
}
