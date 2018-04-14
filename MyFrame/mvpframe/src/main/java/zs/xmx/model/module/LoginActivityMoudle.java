package zs.xmx.model.module;

import dagger.Module;
import dagger.Provides;
import zs.xmx.presenter.LoginPresenter;
import zs.xmx.view.activity.LoginActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/3
 * @本类描述	  主要用于存放对象创建的代码
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */
@Module
public class LoginActivityMoudle {
    LoginActivity activity;

    public LoginActivityMoudle(LoginActivity activity) {
        this.activity = activity;
    }

    //第二步:将new LoginPresenter(activity); 代码放到指定类的指定方法中
    @Provides
    LoginPresenter provideLoginActivityPresenter() {
        return new LoginPresenter(activity);
    }

}
