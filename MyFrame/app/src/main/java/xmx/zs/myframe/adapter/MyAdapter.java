package xmx.zs.myframe.adapter;

import android.content.Context;

import com.zhy.adapter.abslistview.MultiItemTypeAdapter;

import java.util.List;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/10/24 20:23
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * ---------------------------------------------     
 * @更新时间   2016/10/24 
 * @更新说明   ${TODO}
 */
public class MyAdapter extends MultiItemTypeAdapter {
    public MyAdapter(Context context, List datas) {
        super(context, datas);
        addItemViewDelegate(new AItemDelagate());
    }
}
