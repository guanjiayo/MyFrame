package zs.xmx.presenter;

import java.lang.ref.WeakReference;

import zs.xmx.model.IRegisterModel;
import zs.xmx.model.IRegisterModelImpl;
import zs.xmx.net.Rx.databus.RegisterRxBus;
import zs.xmx.view.IRegisterView;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/10 23:09
 * @本类描述	  注册逻辑的Presenter层
 * @内容说明
 *
 */
public class RegisterPresenter<T extends IRegisterView> {
    //1.view 层的引用
    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    WeakReference<T> mView;
    //2.model 层的引用
    IRegisterModel mRegisterModel = new IRegisterModelImpl();

    public RegisterPresenter(T view) {
        this.mView = new WeakReference<T>(view);
        mRegisterModel.loadRegisterData();
    }

    @RegisterRxBus
    public void getRegisterData(String datas) {
        mView.get().registerData(datas);
    }
    //3.执行UI逻辑操作(将数据和UI捆绑起来)
    //    public void fetch(){
    //        if(mView.get()!=null&&mRegisterModel!=null){
    //            mRegisterModel.loadRegisterData(new IRegisterModel.OnRegisterLoadListener() {
    //                @Override
    //                public void onComplete(String datas) {
    //                    mView.get().registerData(datas);
    //                }
    //            });
    //        }
    //    }
}
