package xmx.zs.mvcframe.isolation.normal.ali;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/18 10:15
 * @本类描述	  阿里支付框架实现类
 * @内容说明   使用 AliPayRequest.getInstance().pay()
 *
 */
public class AliPayRequest {

    private static AliPayRequest instance;

    private AliPayRequest() {

    }

    public static AliPayRequest getInstance() {
        if (instance == null) {
            instance = new AliPayRequest();
        }
        return instance;
    }


    public void pay(final Context context, final String orderInfo) {
        final AliPayResultHandler mHandler = new AliPayResultHandler(context);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask((Activity) context);
                //参数:订单id,钱包调起前的loading界面是否显示
                Map<String, String> result = payTask.payV2(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();


    }

}
