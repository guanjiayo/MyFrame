package xmx.zs.newsframe.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xmx.zs.newsframe.R;
import xmx.zs.newsframe.adapter.commont.CommonAdapter;
import xmx.zs.newsframe.adapter.commont.ViewHolder;
import xmx.zs.newsframe.domain.MainItemInfo;

import static xmx.zs.newsframe.R.id.cb;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/2 17:16
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class MainFragment extends BaseFragment {
    private List<MainItemInfo> mData;
    private ListView           mListView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_main, null);
        mListView = (ListView) rootView.findViewById(R.id.lv_mainFragment);
        return rootView;
    }

    @Override
    public void initData() {
        mData = new ArrayList<MainItemInfo>();
        for (int i = 1; i <= 50; i++) {
            mData.add(new MainItemInfo("Android 万能适配器" + i, "ListView 封装测试描述", "2016-10-16"));

        }


        final List<Integer> mPos = new ArrayList<Integer>();
        mListView.setAdapter(new CommonAdapter<MainItemInfo>(mContext, R.layout.item_main, mData) {

            @Override
            protected void convert(final ViewHolder holder, final MainItemInfo item, int position) {
                holder.setText(R.id.tv_title, item.getTitle())//
                        .setText(R.id.tv_desc, item.getDesc())//
                        .setText(R.id.tv_data, item.getData());

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(MainActivity.this, position + 1 + "", Toast.LENGTH_SHORT).show();
                        //       mData.remove(position);
                        mData.add(position, new MainItemInfo("新增一个Item", "hehe", "2016-11-6"));
                        notifyDataSetChanged();
                    }
                });

                final CheckBox checkBox = holder.getViewByID(cb);
                //初始化checkBox时,先默认全部为false
                checkBox.setChecked(false);
                if (mPos.contains(holder.getItemPosition())) {
                    checkBox.setChecked(true);
                }
                //checkBox.setChecked(item.isChecked());
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // item.setChecked(checkBox.isChecked());
                        if (checkBox.isChecked()) {
                            mPos.add(holder.getItemPosition());
                        } else {
                            //注意是移除对象
                            mPos.remove((Integer) holder.getItemPosition());
                        }
                    }
                });
            }
        });
        super.initData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }
}
