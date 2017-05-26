package xmx.zs.myframe.adapter;

import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/10/24 21:50
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * ---------------------------------------------     
 * @更新时间   2016/10/24 
 * @更新说明   ${TODO}
 */
public class AItemDelagate implements ItemViewDelegate {
    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

    @Override
    public boolean isForViewType(Object item, int position) {
        return false;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {

    }
}
