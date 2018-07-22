package xmx.zs.mvcframe.isolation.normal.wechat;

import android.app.Activity;
import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.utils.ToastUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/18 12:43
 * @本类描述	  微信SDK框架实现类
 * @内容说明   使用 WeChatRequest.getInstance().login(this);
 *
 */
public class WeChatRequest {
    private        IWXAPI        mMsgApi;
    private static WeChatRequest instance;

    private WeChatRequest() {

    }

    public static WeChatRequest getInstance() {
        if (instance == null) {
            instance = new WeChatRequest();
        }
        return instance;
    }

    public void pay(Context context, String orderInfo) {
        //注册微信支付
        mMsgApi = WXAPIFactory.createWXAPI(context, null);
        mMsgApi.registerApp(context.getResources().getString(R.string.weixin_api_key));
        //处理服务器返回的拼接好的微信订单Json信息
        try {
            final JSONObject jsonObject = new JSONObject(orderInfo);
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayReq req = new PayReq();
                    //拼接服务器返回的订单信
                    req.appId = jsonObject.optString("appid");
                    req.partnerId = jsonObject.optString("partnerid");
                    req.prepayId = jsonObject.optString("prepayid");
                    req.nonceStr = jsonObject.optString("noncestr");
                    req.timeStamp = jsonObject.optInt("timestamp") + "";
                    req.packageValue = jsonObject.optString("package");
                    req.sign = jsonObject.optString("sign");
                    mMsgApi.sendReq(req);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void login(Context context) {
        //注册微信支付 (上下文,APPID,是否检查签名)
        mMsgApi = WXAPIFactory.createWXAPI(context, context.getResources().getString(R.string.weixin_api_key), true);
        mMsgApi.registerApp(context.getResources().getString(R.string.weixin_api_key));
        if (!mMsgApi.isWXAppInstalled()) {
            //未安装微信
            ToastUtils.showToast((Activity) context, "请先安装微信");
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            //应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
            req.scope = "snsapi_userinfo";
            //用于保持请求和回调的状态，授权请求后原样带回给第三方。
            //该参数可用于防止csrf攻击（跨站请求伪造攻击，
            //建议第三方带上该参数，可设置为简单的随机数加session进行校验
            req.state = "wechat_sdk_demo_test";
            mMsgApi.sendReq(req);
        }
    }

    /**
     * 注销登录
     */
    public void logout() {

        mMsgApi.unregisterApp();
    }
}
