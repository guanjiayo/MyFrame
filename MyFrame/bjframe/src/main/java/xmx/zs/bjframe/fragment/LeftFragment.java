package xmx.zs.bjframe.fragment;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xmx.zs.bjframe.R;
import xmx.zs.bjframe.activity.TwoActivity;
import xmx.zs.bjframe.adapter.LeftAdapter;
import xmx.zs.bjframe.base.BaseFragment;
import xmx.zs.bjframe.domain.LeftItemInfo;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/6 21:09
 * @本类描述	  leftFragment
 * @内容说明  1.这里我们的LeftFragmet 直接打气筒打上ListView
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class LeftFragment extends BaseFragment {
    private ListView           mListView;
    private LeftAdapter        mLeftAdapter;
    private List<LeftItemInfo> data;

    public static LeftFragment newInstance(){
        return new LeftFragment();
    }
    @Override
    protected View initView() {
        mListView = new ListView(mContext);
        //  mListView.setBackgroundColor(Color.BLACK);// 设置背景黑色
        mListView.setDividerHeight(0);// 没有分割线
        // mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 设置选中的背景色为透明
        //  mListView.setPadding(0, 50, 0, 0);// 设置顶部的padding为50

        return mListView;
    }

    @Override
    public void initData() {
        //这里先设置虚拟数据,实际传服务器数据
        data = new ArrayList<LeftItemInfo>();
        data.add(new LeftItemInfo("附一"));
        data.add(new LeftItemInfo("附二"));
        mLeftAdapter = new LeftAdapter(mContext, R.layout.item_left, data);
        mListView.setAdapter(mLeftAdapter);
        mLeftAdapter.notifyDataSetChanged();
        super.initData();
    }

    @Override
    public void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mContext.showToast(data.get(position).toString());
                switch (position) {
                    case 0:
                        toOtherFragment(R.id.fl_main, new FirstFragment());
                        break;
                    case 1:
                        startActivity(new Intent(mContext, TwoActivity.class));
                        break;

                    default:
                        return;
                }

                mContext.mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });


        super.initEvent();
    }
}
