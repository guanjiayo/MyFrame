package xmx.zs.newsframe.utils;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/1 22:18
 * @本类描述	  Activity跳转界面工具类
 * @内容说明   Activity之间跳转,以及跳转动画
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class ActivityUtils extends AppCompatActivity {
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        //  overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        //   overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        //    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 带有右进右出动画的退出
     */
    @Override
    public void finish() {
        super.finish();
        //    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 默认退出
     */
    public void defaultFinish() {
        super.finish();
    }
}
