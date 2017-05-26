package zs.xmx.testpro;

import android.app.Activity;
import android.os.Bundle;

import zs.xmx.testpro.view.EditTextExpand;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/16 17:19
 * @本类描述	  仿网页的注册登陆页面
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class LoginActivity extends Activity {
    private EditTextExpand ee1;
    private EditTextExpand ee2;
    private EditTextExpand ee3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        setContentView(R.layout.activity_login);
        ee1 = (EditTextExpand) findViewById(R.id.ee1);
        ee2 = (EditTextExpand) findViewById(R.id.ee2);
        ee3 = (EditTextExpand) findViewById(R.id.ee3);

    }
}
