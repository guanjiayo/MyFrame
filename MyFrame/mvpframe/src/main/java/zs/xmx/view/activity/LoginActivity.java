package zs.xmx.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import zs.xmx.R;
import zs.xmx.dagger2.component.DaggerILoginActivityComponent;
import zs.xmx.model.module.LoginActivityMoudle;
import zs.xmx.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_username;
    EditText et_pwd;
    Button   mBtnLogin;

    private ProgressDialog dialog;

    //第一步:指定需要注入的目标,下面就不需要new 的方式将P层和V层关联
    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        dialog = new ProgressDialog(this);

        //此处会实例化具体P层的对象,一旦new出来,两层就会耦合到一起
        //mPresenter = new LoginPresenter(this);

        DaggerILoginActivityComponent component = (DaggerILoginActivityComponent) DaggerILoginActivityComponent.builder().
                loginActivityMoudle(new LoginActivityMoudle(this))
                .build();
        component.in(this);
    }

    private void initView() {

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

    }


    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "欢迎登陆", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onClick(View v) {
        String username = et_username.getText().toString();
        String password = et_pwd.getText().toString();

        boolean checkUserInfo = checkUserInfo(username, password);

        if (checkUserInfo) {
            dialog.show();
            mPresenter.login(username, password);
        } else {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
