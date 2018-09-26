package zs.xmx.mvpframe.dagger.module;

import dagger.Module;
import dagger.Provides;
import zs.xmx.mvpframe.presenter.LoginPresenter;
import zs.xmx.mvpframe.presenter.RegisterPresenter;
import zs.xmx.mvpframe.view.IView;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/25 17:10
 * @本类描述	  Module 基类
 * @内容说明
 *
 */
@Module
public class BasePresenterModule {
    private IView mView;

    public BasePresenterModule(IView view) {
        mView = view;
    }


    @Provides
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(mView);
    }

    @Provides
    public RegisterPresenter provideRegisterPresenter() {
        return new RegisterPresenter(mView);
    }


}
