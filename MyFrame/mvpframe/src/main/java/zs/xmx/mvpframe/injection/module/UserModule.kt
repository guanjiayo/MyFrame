package zs.xmx.mvpframe.injection.module

import dagger.Module
import dagger.Provides
import zs.xmx.mvpframe.model.IUserModel
import zs.xmx.mvpframe.model.impl.UserModelImpl

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 18:24
 * @本类描述	  用户业务层的Module
 * @内容说明
 *
 */
@Module
class UserModule {
//    /**
//     * Presenter 层的
//     * 相当于 var mUserModel : IUserModel = UserModelImpl()
//     */
//    @Provides
//    fun provideUserService(mUserModel: UserModelImpl): IUserModel {
//        return mUserModel
//    }

    /**
     * Presenter 层的
     * 相当于 var mUserModel : IUserModel = UserModelImpl()
     */
    @Provides
    fun provideUserModel(mUserModel: UserModelImpl): IUserModel {
        return mUserModel
    }
}