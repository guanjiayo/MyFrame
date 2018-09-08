package zs.xmx.mvpframe.isolation.normal.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import zs.xmx.mvpframe.utils.init.ProjectInit;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/20 23:36
 * @本类描述	  腾讯SDK框架实现类
 * @内容说明   1.使用 TencentRequest.getDefault().login(this);
 *            2. 同时要在 Activity的onActivityResult:
 *            TencentRequest.getDefault().onActivityResultData(requestCode, resultCode, data);
 *            3. 分享需要在onDestroy() TencentRequest.getDefault().releaseResource释放资源
 *
 *  Activity的onActivityResult是请求的结果回调,可在那里进行其他操作(如登录成功)
 *
 */
public class TencentRequest {
    private static TencentRequest       instance;
    private static Tencent              mTencent;
    private        TencentLoginListener mTencentLoginListener;
    private        TencentShareListener mTencentShareListener;
    private Bundle mBundle = new Bundle();

    private TencentRequest() {

    }

    public static TencentRequest getInstance() {
        mTencent = Tencent.createInstance("1105229361", ProjectInit.getApplicationContext());
        if (instance == null) {
            instance = new TencentRequest();
        }

        return instance;
    }

    /**
     * 判断手机是否已经安装QQ
     *
     * @param context
     * @return
     */
    public boolean isQQInstalled(Context context) {
        return mTencent.isQQInstalled(context);
    }

    /**
     * 判断是否支持SSO登录
     *
     * @param context
     * @return
     */
    public boolean isSupportSSOLogin(Context context) {
        return mTencent.isSupportSSOLogin((Activity) context);
    }

    /**
     * 登录
     *
     * @param context
     */
    public void login(Context context) {
        mTencentLoginListener = new TencentLoginListener(context, mTencent);
        if (!mTencent.isSessionValid()) {
            /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
             官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
             第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
            /**
             * @param1 上下文
             * @param2 应用需要获得哪些API权限
             * @param3 IUIListener接口回调
             * @param4 是否使用扫码登录
             */
            mTencent.login((Activity) context, "all", mTencentLoginListener, false);
        }
    }

    public void onActivityResultData(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, mTencentLoginListener);
    }

    /**
     * 释放资源
     */
    public void releaseResource() {
        if (mTencent != null) {
            mTencent.releaseResource();
        }
    }

    /**
     * 注销登录
     *
     * @param context
     */
    public void logout(Context context) {
        mTencent.logout(context);
    }

    /**
     * 分享链接
     *
     * @param context
     */
    public void shareUrlToQQ(Context context) {
        mTencentShareListener = new TencentShareListener(context, mTencent);
        if (mBundle != null) {
            mBundle.clear();
        }
        //分享的类型
        mBundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        //这条分享消息被好友点击后的跳转URL。
        mBundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_SUMMARY不能全为空，最少必须有一个是有值的。
        mBundle.putString(QQShare.SHARE_TO_QQ_TITLE, "我在测试");
        //分享的图片URL
        mBundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        //分享的消息摘要，最长50个字
        mBundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "测试");
        //手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        mBundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "??我在测试");
        //标识该消息的来源应用，值为应用名称+AppId。
        //bundle.putString(QQShare.Ap, "星期几" + AppId);

        mTencent.shareToQQ((Activity) context, mBundle, mTencentShareListener);
    }

    /**
     * 分享音乐
     */
    public void shareAudioToQQ(Context context) {
        mTencentShareListener = new TencentShareListener(context, mTencent);
        if (mBundle != null) {
            mBundle.clear();
        }
        //分享的类型
        mBundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        //这条分享消息被好友点击后的跳转URL。
        mBundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://c.y.qq.com/v8/playsong.html?songid=109325260&songmid=000kuo2H2xJqfA&songtype=0&source=mqq&_wv=1");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_SUMMARY不能全为空，最少必须有一个是有值的。
        mBundle.putString(QQShare.SHARE_TO_QQ_TITLE, "不要每天陪我聊天因为我害怕会喜欢上你");
        //分享的图片URL
        mBundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://y.gtimg.cn/music/photo_new/T002R300x300M000003KIU6V02sS7C.jpg?max_age=2592000");
        //分享的消息摘要，最长50个字
        mBundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "乔紫乔");
        //手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
        mBundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "QQ音乐");
        //音乐链接
        mBundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, "http://ws.stream.qqmusic.qq.com/C100000kuo2H2xJqfA.m4a?fromtag=0");

        mTencent.shareToQQ((Activity) context, mBundle, mTencentShareListener);
    }

    /**
     * 分享本地图片
     */
    public void shareLocalePicToQQ(Context context, String url) {
        mTencentShareListener = new TencentShareListener(context, mTencent);
        if (mBundle != null) {
            mBundle.clear();
        }
        //分享的类型
        mBundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        mBundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, url);
        mTencent.shareToQQ((Activity) context, mBundle, mTencentShareListener);
    }

    /**
     * 分享应用
     */
    public void shareAppToQQ(Context context) {
        mTencentShareListener = new TencentShareListener(context, mTencent);
        if (mBundle != null) {
            mBundle.clear();
        }
        //分享的类型
        mBundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        //这条分享消息被好友点击后的跳转URL。
        mBundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://url.cn/424xgot");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_SUMMARY不能全为空，最少必须有一个是有值的。
        mBundle.putString(QQShare.SHARE_TO_QQ_TITLE, "【推荐】《Microsoft Excel》");
        //分享的图片URL
        mBundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://url.cn/424xgoi");
        //分享的消息摘要，最长50个字
        mBundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "办公|57.4MB|785万次下载|4.6/5星");
        mTencent.shareToQQ((Activity) context, mBundle, mTencentShareListener);
    }

    /**
     * 直接分享到QQ空间
     *
     * @param context
     */
    public void shareToQZone(final Context context) {
        mTencentShareListener = new TencentShareListener(context, mTencent);
        if (mBundle != null) {
            mBundle.clear();
        }
        //分享类型 (图文)
        mBundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        //分享标题
        mBundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, "空间分享");
        //要跳转的URL
        mBundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        //要显示的内容
        mBundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "QQ空间分享");
        //分享的图片
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        mBundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        mTencent.shareToQzone((Activity) context, mBundle, mTencentShareListener);
    }

}
