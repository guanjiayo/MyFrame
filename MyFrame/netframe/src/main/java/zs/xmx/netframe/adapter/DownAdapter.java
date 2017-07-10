package zs.xmx.netframe.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.DownInfo;



public class DownAdapter extends RecyclerArrayAdapter<DownInfo> {

    public DownAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DownHolder(parent);
    }

}