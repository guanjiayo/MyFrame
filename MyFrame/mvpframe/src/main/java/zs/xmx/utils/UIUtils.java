package zs.xmx.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/7/11 13:53
 * @本类描述	  取得缩放系数工具类
 * @内容说明   这里假定设计给的是1232*720 ---》实际设备是800*600
 *
 */
public class UIUtils {
    public final float STANDARD_WIDTH  = 720;
    public final float STANDARD_HEIGHT = 1232;


    //实际设备宽高
    public float displayMetricsWidth;
    public float displayMetricsHeight;
    private String DIMEN_CLASS = "com.android.internal.R$dimen";


    public UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //加载当前界面信息
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        /**displayMetrics.heightPixels已经去除了虚拟按键的高度**/
        //高度提取，如果有虚拟按键，那么减去了虚拟按键的长度
        //Log.i("barry", "height:" + displayMetrics.heightPixels);

        /**拿到设备实际的宽高**/
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            //获取状态框信息
            int systemBarHeight = getValue(context, "system_bar_height", 48);

            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            } else {
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            }

        }
    }

    /**
     * 获取比例系数
     *
     * @param context
     * @param systemId
     * @param defValue
     * @return
     */
    public int getValue(Context context, String systemId, int defValue) {

        try {
            Class<?> clazz = Class.forName(DIMEN_CLASS);
            Object r = clazz.newInstance();
            Field field = clazz.getField(systemId);
            int x = (int) field.get(r);
            return context.getResources().getDimensionPixelOffset(x);

        } catch (Exception e) {
            return defValue;
        }
    }


}
