package zs.xmx.mvpframe.isolation.normal.tencent;

import android.content.Context;

import com.tencent.connect.UnionInfo;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/19 12:23
 * @本类描述	  腾讯登录接口监听
 * @内容说明   调用登录之后的操作都在这个类进行
 *
 */
public class TencentLoginListener implements IUiListener {
    private Context mContext;
    private Tencent mTencent;

    TencentLoginListener(Context context, Tencent tencent) {
        mContext = context;
        mTencent = tencent;
    }

    @Override
    public void onComplete(Object respond) {
        JSONObject jsonResponse = (JSONObject) respond;
        //登录成功,返回一个JSON对象
        if (null == respond || jsonResponse.length() == 0) {
            return;
        }
        //给Tencent设置OpenID和Token
        initOpenIdAndToken(respond);
        //获取用户信息
        getUseInfo();
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }

    /**
     * 给Tencent设置OpenID和Token
     * <p>
     * 使用UseInfo获取用户信息,需要先设置OpenID和Token
     */
    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        String openID = jb.optString("openid");  //openid用户唯一标识
        String access_token = jb.optString("access_token");
        String expires = jb.optString("expires_in");
        mTencent.setOpenId(openID);
        mTencent.setAccessToken(access_token, expires);
    }

    /**
     * 获取用户信息
     */
    private void getUseInfo() {
        UserInfo userInfo = new UserInfo(mContext, mTencent.getQQToken());
        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object respond) {
                //返回的是JsonObject 用户信息
                JSONObject jsonObject = (JSONObject) respond;
                String figureurl = jsonObject.optString("figureurl");
                String nickname = jsonObject.optString("nickname");
                String gender = jsonObject.optString("gender");
                String msg = jsonObject.optString("msg");
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    /**
     * 获取登录唯一的标识
     */
    private void getUnionId() {
        UnionInfo unionInfo = new UnionInfo(mContext, mTencent.getQQToken());
        unionInfo.getUnionId(new IUiListener() {
            @Override
            public void onComplete(Object respond) {
                //返回的是JsonObject 用户信息
                JSONObject jsonObject = (JSONObject) respond;
                String unionid = jsonObject.optString("unionid");

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
