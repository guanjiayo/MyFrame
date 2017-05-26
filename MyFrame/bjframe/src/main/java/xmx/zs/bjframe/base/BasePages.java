package xmx.zs.bjframe.base;

import android.view.View;
import android.widget.FrameLayout;

import xmx.zs.bjframe.R;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/11 22:23
 * @本类描述	  Page 基类
 * @内容说明   ${TODO}
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public class BasePages {
    protected HomeActivity mContext;
    private   View         rootView;
    protected FrameLayout  fl_content;

    //有上下才有后续操作
    public BasePages(HomeActivity context) {
        this.mContext = context;
        initView();

    }

    public void initView() {
        rootView = View.inflate(mContext, R.layout.page_base, null);
        fl_content = (FrameLayout) rootView.findViewById(R.id.fl_basepage_content);
    }

    /**
     * 哪个需要显示数据,哪个覆盖此方法
     */
    public void initData() {

    }


    /**
     * 设置显示的 条目 对应的页面
     * <p>
     * 让子类覆盖实现
     *
     * @param pageIndex
     */
    public void selectPage(int pageIndex) {
    }

    /**
     * 得到根布局
     *
     * @return
     */
    public View getRootView() {
        return rootView;

    }
}
