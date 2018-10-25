package xmx.zs.mvcframe.view.activity;

import android.content.Intent;
import com.google.android.material.textfield.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.base.BaseActivity;
import xmx.zs.mvcframe.isolation.normal.tencent.TencentRequest;
import xmx.zs.mvcframe.isolation.normal.wechat.WeChatRequest;
import xmx.zs.mvcframe.utils.RegularUtils;


/**
 * todo 修复EditText扩展库,重新放进去
 */
public class LoginActivity extends BaseActivity {

    EditText        mEt_Username;
    TextInputLayout mTil_Username;
    EditText        mEt_Password;
    TextInputLayout mTil_Pwd;
    Button          mButton;


    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mEt_Username = (EditText) findViewById(R.id.et_username);
        mTil_Username = (TextInputLayout) findViewById(R.id.til_username);
        mEt_Password = (EditText) findViewById(R.id.et_password);
        mTil_Pwd = (TextInputLayout) findViewById(R.id.til_pwd);
        mButton = (Button) findViewById(R.id.btn_login);


    }

    @Override
    protected void initData() {

    }

    String password;
    String userName;

    @Override
    protected void initEvent() {
        mButton.setOnClickListener(this);
        findViewById(R.id.btn_qq_login).setOnClickListener(this);
        findViewById(R.id.btn_wechat_login).setOnClickListener(this);

        mEt_Username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                userName = mEt_Username.getText().toString();
                if (!hasFocus) {
                    if (!TextUtils.isEmpty(userName)) {

                        if (!RegularUtils.isUserName(userName)) {

                            mTil_Username.setError("用户名取值范围为a-z,A-Z,0-9,\"_\",汉字，不能以\"_\"结尾,用户名必须是6-20位");
                        }
                    }
                } else {
                    mTil_Username.setErrorEnabled(false);
                }
            }
        });

        mEt_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt_Password.setFocusable(true);//设置输入框可聚集
                mEt_Password.setFocusableInTouchMode(true);//设置触摸聚焦
                mEt_Password.requestFocus();//请求焦点
                mEt_Password.findFocus();//获取焦点
            }
        });

        mEt_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                password = mEt_Password.getText().toString();
                if (!hasFocus) {
                    if (!TextUtils.isEmpty(password)) {

                        if (!RegularUtils.isPassword(password)) {

                            mTil_Pwd.setError("以字母开头，长度在6~18之间，只能包含字母、数字和下划线");
                        }
                    }
                } else {
                    mTil_Pwd.setErrorEnabled(false);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        //        if (RegularUtils.isUserName(userName) && RegularUtils.isPassword(password)) {
        //            //TODO ActivityUtils改回来
        //            startActivity(HomeActivity.class);
        //
        //        }
        switch (v.getId()) {
            case R.id.btn_login://普通登录
                startActivity(MainActivity.class);
                break;
            case R.id.btn_wechat_login://微信登录
                WeChatRequest.getInstance().login(this);
                break;
            case R.id.btn_qq_login://qq登录
                showToast("SSO登录:" + TencentRequest.getInstance().isSupportSSOLogin(this));
                TencentRequest.getInstance().login(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TencentRequest.getInstance().onActivityResultData(requestCode, resultCode, data);
    }

}
