package xmx.zs.bjframe.fragment;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

import xmx.zs.bjframe.Pages.HomePage;
import xmx.zs.bjframe.Pages.MessagePage;
import xmx.zs.bjframe.Pages.NewsPage;
import xmx.zs.bjframe.Pages.ServicePage;
import xmx.zs.bjframe.Pages.SettingPage;
import xmx.zs.bjframe.R;
import xmx.zs.bjframe.adapter.MyPageAdapter;
import xmx.zs.bjframe.base.BaseFragment;
import xmx.zs.bjframe.base.BasePages;
import xmx.zs.bjframe.view.LazyViewPager;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/2 17:16
 * @本类描述	  MainFragment 主页的片段
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class MainFragment extends BaseFragment {
    private RadioGroup    rg_radios;
    private LazyViewPager vp_pages;
    public  List<BasePages> mPages      = new ArrayList<>();
    private int             selectIndex = 0; //默认索引值

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_main, null);
        rg_radios = (RadioGroup) rootView.findViewById(R.id.rg_radios);
        vp_pages = (LazyViewPager) rootView.findViewById(R.id.vp_mainfragment_pages);
        return rootView;
    }

    @Override
    public void initData() {
        initCurrentPage();
        mPages.add(new HomePage(mContext));
        mPages.add(new NewsPage(mContext));
        mPages.add(new ServicePage(mContext));
        mPages.add(new MessagePage(mContext));
        mPages.add(new SettingPage(mContext));

        vp_pages.setAdapter(new MyPageAdapter(mPages));

    }

    /**
     * 初始化当前页面
     */
    private void initCurrentPage() {
        switch (selectIndex) {
            case 0:
                rg_radios.check(R.id.rb_home);
                break;
            default:
                break;
        }
    }


    @Override
    public void initEvent() {
        // 事件设置
        rg_radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rb_home:
                        selectIndex = 0;
                        break;
                    case R.id.rb_news:
                        selectIndex = 1;
                        break;
                    case R.id.rb_smartservice:
                        selectIndex = 2;
                        break;
                    case R.id.rb_govaffiar:
                        selectIndex = 3;
                        break;
                    case R.id.rb_settingcenter:
                        selectIndex = 4;
                        break;

                    default:
                        break;
                }// end switch
                switchPages();
            }
        });
        super.initEvent();
    }

    /**
     * 获取选中的页面
     */
    public BasePages getSelectPage() {
        return mPages.get(selectIndex);
    }

    /**
     * 设置当前 点击RadioButton 显示 对应的View
     */
    public void switchPages() {

        vp_pages.setCurrentItem(selectIndex);


    }


}
