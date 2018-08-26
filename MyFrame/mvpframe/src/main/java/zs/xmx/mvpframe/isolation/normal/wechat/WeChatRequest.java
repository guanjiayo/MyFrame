package zs.xmx.mvpframe.isolation.normal.wechat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import zs.xmx.mvpframe.R;
import zs.xmx.mvpframe.utils.ToastUtils;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/18 12:43
 * @本类描述	  微信SDK框架实现类
 * @内容说明   使用 WeChatRequest.getInstance().login(this);
 *
 * //todo 待优化封装以及测试
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
        // mMsgApi = WXAPIFactory.createWXAPI(context, null);
        //  mMsgApi.registerApp(context.getResources().getString(R.string.weixin_api_key));
        init(context);
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
        init(context);

        final SendAuth.Req req = new SendAuth.Req();
        //应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
        req.scope = "snsapi_userinfo";
        //用于保持请求和回调的状态，授权请求后原样带回给第三方。
        //该参数可用于防止csrf攻击（跨站请求伪造攻击，
        //建议第三方带上该参数，可设置为简单的随机数加session进行校验
        req.state = "wechat_sdk_demo_test";
        mMsgApi.sendReq(req);

    }

    /**
     * 分享网页到好友界面
     *
     * @param type 分享场景类型
     */
    public void shareUrl(Context context, int type) {
        //处刷一个WXWebpageObject对象,填写Url
        WXWebpageObject webpage = new WXWebpageObject();
        //分享的url
        webpage.webpageUrl = "网页Url";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        //网页标题
        msg.title = "网页标题";
        //网页描述
        msg.description = "网页描述";
        //图片(图片需要压缩处理一下)
        //Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.show_ic);
        //msg.thumbData = getWXThumb(thumb).toByteArray();

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        /**
         * 分享的场景:
         * 发送到聊天界面——WXSceneSession 0
         * 发送到朋友圈——WXSceneTimeline 1
         * 添加到微信收藏——WXSceneFavorite 2
         */
        switch (type) {
            case 0:
                req.scene = SendMessageToWX.Req.WXSceneSession;//好友聊天界面
                break;
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈界面
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;//微信收藏界面
                break;
        }
        req.message = msg;
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        mMsgApi.sendReq(req);
    }

    /**
     * 分享文字
     *
     * @param type 分享场景类型
     */
    public void shareText(int type) {
        //处刷一个WXTextObject对象,填写分享的文本内容
        WXTextObject textObject = new WXTextObject();
        textObject.text = "要分享的文本";

        //用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        //网页标题
        msg.mediaObject = textObject;
        //网页描述
        msg.description = "文本描述";

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        /**
         * 分享的场景:
         * 发送到聊天界面——WXSceneSession 0
         * 发送到朋友圈——WXSceneTimeline 1
         * 添加到微信收藏——WXSceneFavorite 2
         */
        switch (type) {
            case 0:
                req.scene = SendMessageToWX.Req.WXSceneSession;//好友聊天界面
                break;
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈界面
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;//微信收藏界面
                break;
        }
        req.message = msg;
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        mMsgApi.sendReq(req);
    }

    /**
     * 分享图片
     *
     * @param type 分享场景类型
     */
    public void shareBitmap(Context context, int type) {

        Bitmap pic = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
        //初始化WXImageObject和WXMediaMessage对象
        //这个构造方法中自动把传入的bitmap转化为2进制数据,或者你直接传入byte[]也行
        //注意传入的数据不能大于10M,开发文档上写的
        WXImageObject imageObject = new WXImageObject(pic);
        WXMediaMessage msg = new WXMediaMessage();  //这个对象是用来包裹发送信息的对象
        msg.mediaObject = imageObject;
        //msg.mediaObject实际上是个IMediaObject对象,
        //它有很多实现类,每一种实现类对应一种发送的信息,
        //比如WXTextObject对应发送的信息是文字,想要发送文字直接传入WXTextObject对象就行


        Bitmap thumbBitmap = Bitmap.createScaledBitmap(pic, 150, 150, true);

        //msg.thumbData = bitmap2ByteArray(thumbBitmap);

        //在这设置缩略图
        //官方文档介绍这个bitmap不能超过32kb
        //如果一个像素是8bit的话换算成正方形的bitmap则边长不超过181像素,边长设置成150是比较保险的
        //或者使用msg.setThumbImage(thumbBitmap);省去自己转换二进制数据的过程
        //如果超过32kb则抛异常

        SendMessageToWX.Req req = new SendMessageToWX.Req();    //创建一个请求对象
        req.message = msg;  //把msg放入请求对象中
        /**
         * 分享的场景:
         * 发送到聊天界面——WXSceneSession 0
         * 发送到朋友圈——WXSceneTimeline 1
         * 添加到微信收藏——WXSceneFavorite 2
         */
        switch (type) {
            case 0:
                req.scene = SendMessageToWX.Req.WXSceneSession;//好友聊天界面
                break;
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈界面
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;//微信收藏界面
                break;
        }
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        mMsgApi.sendReq(req);
    }

    /**
     * 分享到小程序
     *
     * @param context
     */
    public void shareMiniProgram(Context context) {
        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
        miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
        miniProgramObj.userName = "gh_d43f693ca31f";     // 小程序原始id
        miniProgramObj.path = "/pages/media";            //小程序页面路径
        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
        msg.title = "小程序消息Title";                    // 小程序消息title
        msg.description = "小程序消息Desc";               // 小程序消息desc
        //msg.thumbData = getThumb();                      // 小程序消息封面图片，小于128k

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
        mMsgApi.sendReq(req);
    }

    /**
     * 分享音乐
     *
     * @param context
     * @param type
     */
    public void shareMusic(Context context, int type) {
        //处刷一个WXTextObject对象,填写分享的文本内容
        WXMusicObject musicObj = new WXMusicObject();
        musicObj.musicUrl = "音乐Url";

        //用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = musicObj;
        //音乐标题
        msg.title = "音乐标题";
        //音乐描述
        msg.description = "音乐描述";
        //音乐缩略图
        //msg.thumbData="Bimap"

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        /**
         * 分享的场景:
         * 发送到聊天界面——WXSceneSession 0
         * 发送到朋友圈——WXSceneTimeline 1
         * 添加到微信收藏——WXSceneFavorite 2
         */
        switch (type) {
            case 0:
                req.scene = SendMessageToWX.Req.WXSceneSession;//好友聊天界面
                break;
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈界面
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;//微信收藏界面
                break;
        }
        req.message = msg;
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        mMsgApi.sendReq(req);
    }

    /**
     * 分享视频
     *
     * @param context
     * @param type
     */
    public void shareVideo(Context context, int type) {
        //处刷一个WXVideoObject对象,填写url
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = "视频Url";

        //用WXVideoObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(video);
        //视频标题
        msg.title = "视频标题";
        //视频描述
        msg.description = "视频描述";
        //音乐缩略图
        //msg.thumbData="Bimap"

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        /**
         * 分享的场景:
         * 发送到聊天界面——WXSceneSession 0
         * 发送到朋友圈——WXSceneTimeline 1
         * 添加到微信收藏——WXSceneFavorite 2
         */
        switch (type) {
            case 0:
                req.scene = SendMessageToWX.Req.WXSceneSession;//好友聊天界面
                break;
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈界面
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;//微信收藏界面
                break;
        }
        req.message = msg;
        //transaction字段用于唯一标志(一个请求)
        req.transaction = String.valueOf(System.currentTimeMillis());
        mMsgApi.sendReq(req);
    }


    /**
     * 初始化微信账号信息
     */
    private void init(Context context) {
        //注册微信支付 (上下文,APPID,是否检查签名)
        mMsgApi = WXAPIFactory.createWXAPI(context, context.getResources().getString(R.string.weixin_api_key), true);
        mMsgApi.registerApp(context.getResources().getString(R.string.weixin_api_key));
        if (!mMsgApi.isWXAppInstalled()) {
            //未安装微信
            ToastUtils.showToast((Activity) context, "请先安装微信");
        }
    }

    /**
     * 注销登录
     */
    public void logout() {

        mMsgApi.unregisterApp();
    }
}
