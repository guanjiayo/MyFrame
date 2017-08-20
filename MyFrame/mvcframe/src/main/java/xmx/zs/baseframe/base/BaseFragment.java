package xmx.zs.baseframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/1 20:11
 * @本类描述	  Fragment 基类
 * @内容说明  Fragment管理交由依赖的Activity管理
 *           //TODO Fragment的增删该操作写一个工具类FragmentUtils操作
 * @补充内容
 *
 * ---------------------------------     
 * @新增内容
 *
 */
public abstract class BaseFragment extends Fragment {

    protected HomeActivity mContext;

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //获取上下文
        mContext = (HomeActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    /**
     * Fragment中,用onCreateView()拿到布局xml
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        //让子类覆盖
        return initLayout();
    }

    protected abstract View initLayout();

    /**
     * Fragment中,onViewCreated()初始化View对象
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 只要Activity 没有销毁   该方法不会重复调用
        initData();
        initEvent();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 子类覆盖此方法来完成数据的初始化
     */
    public void initData() {

    }


    /**
     * 子类覆盖此方法来完成事件的设置
     */
    public void initEvent() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
