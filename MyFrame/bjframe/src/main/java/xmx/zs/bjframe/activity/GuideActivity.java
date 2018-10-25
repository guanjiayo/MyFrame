package xmx.zs.bjframe.activity;

import android.content.Intent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import xmx.zs.bjframe.R;
import xmx.zs.bjframe.base.BaseActivity;
import xmx.zs.bjframe.base.HomeActivity;
import xmx.zs.bjframe.constant.MyConstant;
import xmx.zs.bjframe.utils.DensityUtils;
import xmx.zs.bjframe.utils.SPUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/10 0:48
 * @本类描述	  引导页
 * @内容说明   ViewPager 滑动切换
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class GuideActivity extends BaseActivity {
    private ViewPager    vp_set;
    private LinearLayout ll_graypoints;
    private Button       btn_expr;
    private View         v_redpoint;
    // ViewPager 资源数组
    private int[] pages_id = new int[]{R.mipmap.gui1,
            R.mipmap.gui2, R.mipmap.gui3};
    // 要显示的资源 集合
    List<ImageView> mIV_datas = new ArrayList<ImageView>();
    // 点与点间的距离
    private int mPointDis;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        vp_set = (ViewPager) findViewById(R.id.vp_guid_setPages);
        ll_graypoints = (LinearLayout) findViewById(R.id.ll_guid_graypoints);
        btn_expr = (Button) findViewById(R.id.bt_guid_expr);
        v_redpoint = findViewById(R.id.v_guid_redpoint);
    }

    @Override
    protected void initData() {
        // 得到所有图片
        for (int i = 0; i < pages_id.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(pages_id[i]);
            // 填充 屏幕
            iv.setScaleType(ScaleType.FIT_XY);
            mIV_datas.add(iv);

            // 由于 点 是 我们一开始自定义的,
            // 那么在其他分辨率的屏幕,可能出现 像素偏移
            // 解决:设备像素到普通像素的转换
            int dis = DensityUtils.dip2px(this, 10);
            // 加点
            View v = new View(this);
            v.setBackgroundResource(R.drawable.v_point_gray);
            // 布局参数 设置 点 的显示大小
            LayoutParams lp = new LayoutParams(dis, dis);
            if (i != 0) {
                // 如果不是第一个点
                // 设置左边的间距为10dp
                lp.leftMargin = dis;
            }
            v.setLayoutParams(lp);

            ll_graypoints.addView(v);
        }
        MyAdapter adapter = new MyAdapter();
        vp_set.setAdapter(adapter);
    }
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mIV_datas.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        // 相当于 getView 方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = mIV_datas.get(position);
            container.addView(v);
            return v;
        }

    }

    @Override
    protected void initEvent() {
        // 按钮事件
        btn_expr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO 修改状态  实际项目要改回true
                SPUtils.putParam(MyConstant.ISSETUPFINISH, false);
                // 进入主界面
                Intent home = new Intent(GuideActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        });
        // 监听 红点的位置,实现红点随页面变化 移动(布局观察者)
        v_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener(){



                    @Override
                    public void onGlobalLayout() {
                        mPointDis = ll_graypoints.getChildAt(1).getLeft()
                                - ll_graypoints.getChildAt(0).getLeft();
                        // 取消观察
                        v_redpoint.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });
        //给 ViewPager 添加滑动事件
        vp_set.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 页面选择完成的调用
                //判断当前 是否为最后一页
                if(position==mIV_datas.size()-1)
                {
                    //显示按钮
                    btn_expr.setVisibility(View.VISIBLE);
                }else
                {
                    //隐藏按钮
                    btn_expr.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 页面滑动的应用
                //position 页面的当前位置
                //positionOffset  比例值
                //positionOffsetPixels  偏移的像素
                FrameLayout.LayoutParams layoutParams=(android.widget.FrameLayout.LayoutParams) v_redpoint.getLayoutParams();
                layoutParams.leftMargin=Math.round(mPointDis*(position+positionOffset));
                v_redpoint.setLayoutParams(layoutParams);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        super.initEvent();
    }
}
