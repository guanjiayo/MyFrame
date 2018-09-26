package zs.xmx.mvpframe.presenter;

import android.support.annotation.NonNull;

import zs.xmx.mvpframe.bus.rx_todo.RxSubscribe_todo;
import zs.xmx.mvpframe.view.IView;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/10 18:06
 * @本类描述	  登录逻辑的Presenter层
 * @内容说明
 *
 */
public class LoginPresenter extends BasePresenter {
    private IView mView;

    public LoginPresenter(IView view) {
        super(view);
        this.mView = view;
    }


    /**
     * 处理json数据
     *
     * @param params
     */
    @RxSubscribe_todo
    public void parseData(@NonNull String params) {

        mView.urlRequestSuccess(params);
    }
}
