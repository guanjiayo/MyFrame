package xmx.zs.mvcframe.wxapi;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.base.BaseActivity;
import xmx.zs.mvcframe.isolation.proxy.net.HttpRequestPresenter;
import xmx.zs.mvcframe.isolation.proxy.net.ICallback;

/**
 * 微信移动端结果处理
 * <p>
 * WXPayEntryActivity名字已经目录不能改变
 * <p>
 * //todo 到时拿公司一个账号测试完Demo需要再稍微封装一下
 * //todo 研究下拦截器优化,尽量一个Activity只写一次网络申请
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected int setContentViewId() {
        return 0;
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, getResources().getString(R.string.weixin_api_key));
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {


    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            }
            finish();
        }

        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取AccessToken
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                Toast.makeText(this, "用户拒绝授权", Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                Toast.makeText(this, "用户取消", Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                //发送失败
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                //不支持错误
                break;
            case BaseResp.ErrCode.ERR_COMM:
                //一般错误
                break;
            default:
                finish();
                break;
        }
    }

    /**
     * 获取AccessToken
     */
    private void getAccessToken(String code) {
        //get请求  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                //.append(Constant.APP_ID) APP_ID
                .append("&secret=")
                //.append(Constant.SECRET) APP_SECRET
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        HttpRequestPresenter.getInstance().get(loginUrl.toString(), null, new ICallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    final String access_token = jsonObject.optString("access_token");
                    final String openid = jsonObject.optString("openid");
                    //获取用户信息
                    getUserInfo(access_token, openid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int errorCode, Throwable t) {

            }
        });

    }

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserInfo(String access_token, String openid) {
        //get请求  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        final String userInfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
        HttpRequestPresenter.getInstance().get(userInfo_url, null, new ICallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    //昵称
                    String nickname = jsonObject.optString("nickname");
                    //性别
                    String sex = jsonObject.optString("sex");
                    //省份
                    String province = jsonObject.optString("province");
                    //城市
                    String city = jsonObject.optString("city");
                    //国家
                    String country = jsonObject.optString("country");
                    //头像
                    String headimgurl = jsonObject.optString("headimgurl");
                    //用户唯一标识
                    String unionid = jsonObject.optString("unionid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorCode, Throwable t) {

            }
        });
    }

}