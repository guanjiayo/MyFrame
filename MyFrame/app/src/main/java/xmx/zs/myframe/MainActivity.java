package xmx.zs.myframe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lv = (ListView) findViewById(R.id.lv);
        final List datas = new ArrayList<String>();
        for (int i = 1; i <= 30; i++) {
            datas.add(i + "");
        }

        lv.setAdapter(new CommonAdapter<String>(this, R.layout.item, datas) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv, item);
            }


        });

    }

}
