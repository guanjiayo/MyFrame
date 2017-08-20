package xmx.zs.baseframe.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import xmx.zs.baseframe.R;
import xmx.zs.baseframe.base.BaseActivity;
import xmx.zs.baseframe.base.HomeActivity;
import xmx.zs.baseframe.utils.RegularUtils;
import zs.xmx.weight.ClearEditText;
import zs.xmx.weight.PasswordEditText;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_username)
    ClearEditText    mEt_Username;
    @BindView(R.id.til_username)
    TextInputLayout  mTil_Username;
    @BindView(R.id.et_password)
    PasswordEditText mEt_Password;
    @BindView(R.id.til_pwd)
    TextInputLayout  mTil_Pwd;
    @BindView(R.id.btn_login)
    Button           mButton;


    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }

    String password;
    String userName;

    @Override
    protected void initListener() {

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


    @OnClick(R.id.btn_login)
    public void onClick() {

        if (RegularUtils.isUserName(userName) && RegularUtils.isPassword(password)) {

            //TODO ActivityUtils改回来

            startActivity(new Intent(mContext, HomeActivity.class));
        }

    }
}
