package xmx.zs.bjframe.Pages;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xmx.zs.bjframe.R;
import xmx.zs.bjframe.adapter.commont.CommonAdapter;
import xmx.zs.bjframe.adapter.commont.ViewHolder;
import xmx.zs.bjframe.base.BasePages;
import xmx.zs.bjframe.base.HomeActivity;
import xmx.zs.bjframe.domain.MainItemInfo;
import xmx.zs.bjframe.utils.Logger;

import static xmx.zs.bjframe.R.id.cb;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/12 0:07
 * @本类描述	  首页Page
 * @内容说明
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class HomePage extends BasePages {
    private List<MainItemInfo> mData;
    private ListView           mListView;

    public HomePage(HomeActivity context) {
        super(context);
    }

    //就是往 Fragmentlayout  加数据
    @Override
    public void initData() {
        Logger.i(TAG,"HomePage");
        fl_content.removeAllViews();
        View rootView = View.inflate(mContext, R.layout.page_home, null);

        mListView = (ListView) rootView.findViewById(R.id.lv_homepage);


        mData = new ArrayList<>();
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


        fl_content.addView(rootView);
        super.initData();
    }


}
