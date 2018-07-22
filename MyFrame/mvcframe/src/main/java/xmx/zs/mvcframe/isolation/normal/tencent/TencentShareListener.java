package xmx.zs.mvcframe.isolation.normal.tencent;

import android.content.Context;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/19 12:23
 * @本类描述	  腾讯分享接口监听
 * @内容说明   调用分享之后的操作都在这个类进行
 *
 */
public class TencentShareListener implements IUiListener {
    private Context mContext;
    private Tencent mTencent;

    TencentShareListener(Context context, Tencent tencent) {
        this.mContext = context;
        this.mTencent = tencent;
    }

    @Override
    public void onComplete(Object respond) {
        JSONObject jsonResponse = (JSONObject) respond;
        //登录成功,返回一个JSON对象
        if (null == respond || jsonResponse.length() == 0) {
            return;
        }

    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }


}
