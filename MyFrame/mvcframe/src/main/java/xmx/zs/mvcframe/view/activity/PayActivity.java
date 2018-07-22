package xmx.zs.mvcframe.view.activity;

import android.view.View;

import xmx.zs.mvcframe.R;
import xmx.zs.mvcframe.base.BaseActivity;
import xmx.zs.mvcframe.isolation.normal.ali.AliPayRequest;
import xmx.zs.mvcframe.isolation.normal.wechat.WeChatRequest;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/18 11:20
 * @本类描述	  支付界面
 * @内容说明
 *
 */
public class PayActivity extends BaseActivity {
    @Override
    protected int setContentViewId() {
        return R.layout.activity_pay;
    }


    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ali_pay://支付宝支付
                AliPayRequest.getInstance().pay(PayActivity.this, "服务器返回的带签名的订单信息");
                break;
            case R.id.btn_wechat_pay://微信支付
                WeChatRequest.getInstance().pay(PayActivity.this, "{\"appid\":\"wx6f3942e3e33acdab\",\"partnerid\":\"1492941592\",\"prepayid\":" +
                        "      \"wx20171123171440f26f858da90685178751\",\"package\":\"Sign=WXPay\"," +
                        "      \"noncestr\":\"hhqam64ui0etqfpc91qejmgee3wp4uut\",\"timestamp\":1511428480" +
                        "      ,\"sign\":\"7AC69ACC824999E22B7D97C94839178E\"}");
                break;
        }
    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_ali_pay).setOnClickListener(this);
        findViewById(R.id.btn_wechat_pay).setOnClickListener(this);


    }
}
