package zs.xmx.mvpframe.presenter;

import android.support.annotation.NonNull;

import zs.xmx.mvpframe.bus.rx_todo.RxSubscribe_todo;
import zs.xmx.mvpframe.utils.Logger;
import zs.xmx.mvpframe.view.IView;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/10 18:06
 * @本类描述	  注册逻辑的Presenter层
 * @内容说明   V层引用放到BasePresenter
 *
 */
public class RegisterPresenter extends BasePresenter {
    private IView mView;

    public RegisterPresenter(IView view) {
        super(view);
        this.mView = view;
    }


    /**
     * 处理json数据
     * @param params
     */
    @RxSubscribe_todo
    public void parseData(@NonNull String params) {
        Logger.e("LoginPresenter", params);
        mView.urlRequestSuccess(params);
    }
}
