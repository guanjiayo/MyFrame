package zs.xmx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * TODO:参数外卖Demo以及即时通讯Demo在深入重构
 * 1. 需要按规范重命名文件名
 * 2. 去除依赖的框架(因为这里是要用用作Base用适应种项目)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
