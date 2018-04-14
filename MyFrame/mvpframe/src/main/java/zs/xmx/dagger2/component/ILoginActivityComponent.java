package zs.xmx.dagger2.component;

import dagger.Component;
import zs.xmx.model.module.LoginActivityMoudle;
import zs.xmx.view.activity.LoginActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2017/8/3
 * @本类描述
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @更新时间   
 * @新增内容   
 *
 */
//第三步:通过接口将创建实例的代码和目标管理
@Component(modules = LoginActivityMoudle.class)
public interface ILoginActivityComponent {
    void in(LoginActivity activity);
}
