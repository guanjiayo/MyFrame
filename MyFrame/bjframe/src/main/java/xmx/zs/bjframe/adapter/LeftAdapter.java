package xmx.zs.bjframe.adapter;

import android.content.Context;

import java.util.List;

import xmx.zs.bjframe.R;
import xmx.zs.bjframe.adapter.commont.CommonAdapter;
import xmx.zs.bjframe.adapter.commont.ViewHolder;
import xmx.zs.bjframe.domain.LeftItemInfo;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/6 21:29
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class LeftAdapter extends CommonAdapter<LeftItemInfo> {
    /**
     * @param context       上下文
     * @param layoutID_item item 布局ID
     * @param datas         List<Bean> bean类数组
     */
    public LeftAdapter(Context context, int layoutID_item, List<LeftItemInfo> datas) {
        super(context, layoutID_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LeftItemInfo item, int position) {
        holder.setText(R.id.tv_item, item.getItem());
    }
}
