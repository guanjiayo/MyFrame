package zs.xmx.view.activity;

import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import zs.xmx.R;
import zs.xmx.base.BaseActivity;
import zs.xmx.dagger2.component.DaggerRegisterComponent;
import zs.xmx.dagger2.module.RegisterPresenterModule;
import zs.xmx.net.Rx.databus.RxBus;
import zs.xmx.presenter.RegisterPresenter;
import zs.xmx.view.IRegisterView;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @Inject
    RegisterPresenter mPresenter;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
        }
    }

    @Override
    protected void initEvent() {
        //new RegisterPresenter(this).fetch();
        //RxBus注册(类似EventBus)
        //mPresenter = new RegisterPresenter(this);
        DaggerRegisterComponent.builder()
                .registerPresenterModule(new RegisterPresenterModule(this))
                .build().inject(this);
        RxBus.getInstance().register(mPresenter);
    }


    @Override
    public void registerData(String datas) {
        Log.i(TAG, "registerData: " + datas);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //RxBus取消注册(类似EventBus)
        RxBus.getInstance().unRegister(mPresenter);
    }
}
