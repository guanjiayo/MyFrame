package xmx.zs.bjframe.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xmx.zs.bjframe.base.BasePages;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/12 0:32
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class MyPageAdapter extends PagerAdapter {
    protected List<BasePages> mDatas;

    public MyPageAdapter(List<BasePages> datas) {
        this.mDatas = datas;
    }



    //        1. getCount() 用来确定多少个数据显示
    @Override
    public int getCount() {
        return mDatas.size();
    }

    //        3. isViewFromObject
    //              1. 缓存复用
    //              2. 判断标记是否一致 一致显示view
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //        2. instantiateItem() 初始化显示View(container 容器,相当于我们要显示的viewpager)
    //              1. 默认缓存3个View  默认前一个;后一个(LazyViewpager,不会预加载)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePages page = (BasePages) mDatas.get(position);
        View v = page.getRootView();
        page.initData(); // 显示哪个页面,初始化哪个数据
        // System.out.println(position);
        container.addView(v);
        return v;
    }

    //        4. destroyItem
    //              1. 不是缓存位置的view要销毁
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
