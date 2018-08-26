package zs.xmx.mvpframe.isolation.normal.ali;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.Map;

import zs.xmx.mvpframe.utils.ToastUtils;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/18 15:02
 * @本类描述	  支付宝移动端支付结果处理
 * @内容说明
 *
 */
public class AliPayResultHandler extends Handler {
    private Context mContext;

    AliPayResultHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleMessage(Message msg) {
        @SuppressWarnings("unchecked")
        AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
        int resultCode = Integer.valueOf(resultStatus);
        ToastUtils.showToast((Activity) mContext, "支付结果" + resultStatus);
        switch (resultCode) {
            case 9000://订单支付成功
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                break;
            //正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
            case 8000:

                break;
            //订单支付失败
            case 4000:
                break;
            //重复请求
            case 5000:
                break;
            //用户中途取消
            case 6001:
                break;
            //网络连接出错
            case 6002:
                break;
            //支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
            case 6004:
                break;
            default:
                break;
        }
    }

}
