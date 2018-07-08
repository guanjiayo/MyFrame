package zs.xmx.permission.utils;

import android.content.Context;
import android.os.Build;

import zs.xmx.permission.callback.ISetting;
import zs.xmx.permission.support.Default;
import zs.xmx.permission.support.HuaWei;
import zs.xmx.permission.support.LG;
import zs.xmx.permission.support.Letv;
import zs.xmx.permission.support.MeiZu;
import zs.xmx.permission.support.OPPO;
import zs.xmx.permission.support.QiHu;
import zs.xmx.permission.support.VIVO;
import zs.xmx.permission.support.XiaoMi;

/**
 * 跳转到手机系统设置权限页面工具类
 */
public class SettingUtils {
    private static final String MANUFACTURER_HUAWEI  = "HUAWEI";//华为
    private static final String MANUFACTURER_MEIZU   = "MEIZU";//魅族
    private static final String MANUFACTURER_XIAOMI  = "XIAOMI";//小米
    private static final String MANUFACTURER_SONY    = "SONY";//索尼
    private static final String MANUFACTURER_OPPO    = "OPPO";
    private static final String MANUFACTURER_LG      = "LG";
    private static final String MANUFACTURER_VIVO    = "VIVO";
    private static final String MANUFACTURER_SAMSUNG = "SAMSUNG";//三星
    private static final String MANUFACTURER_LETV    = "LETV";//乐视
    private static final String MANUFACTURER_ZTE     = "ZTE";//中兴
    private static final String MANUFACTURER_YULONG  = "YULONG";//酷派
    private static final String MANUFACTURER_LENOVO  = "LENOVO";//联想
    private static final String MANUFACTURER_QIHU    = "QIHU";//奇虎360

    /**
     * 跳设置界面
     *
     * @param context context
     */
    public static void go2Setting(Context context) {
        ISetting iSetting;

        switch (Build.MANUFACTURER.toUpperCase()) {
            case MANUFACTURER_VIVO:
                iSetting = new VIVO(context);
                break;
            case MANUFACTURER_OPPO:
                iSetting = new OPPO(context);
                break;
            case MANUFACTURER_HUAWEI:
                iSetting = new HuaWei(context);
                break;
            case MANUFACTURER_QIHU:
                iSetting = new QiHu(context);
                break;
            case MANUFACTURER_MEIZU:
                iSetting = new MeiZu(context);
                break;
            case MANUFACTURER_LG:
                iSetting = new LG(context);
                break;
            case MANUFACTURER_LETV:
                iSetting = new Letv(context);
                break;
            case MANUFACTURER_SONY:
                iSetting = new Letv(context);
                break;
            case MANUFACTURER_XIAOMI:
                iSetting = new XiaoMi(context);
                break;
            default:
                iSetting = new Default(context);
                break;
        }
        if (iSetting.getSetting() == null)
            return;
        context.startActivity(iSetting.getSetting());
    }
}
