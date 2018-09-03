package zs.xmx.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zs.xmx.mvpframe.utils.ToastUtils;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/1 20:11
 * @本类描述	  Fragment 基类
 * @内容说明  Fragment管理交由依赖的Activity管理
 *           //TODO Fragment的增删该操作写一个工具类FragmentUtils操作
 *           //todo 包括栈问题
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
public abstract class BaseFragment extends LazyFragment {
    /**
     * 上下文
     */
    protected HomeActivity mContext;
    /**
     * TAG 这里定义TAG,后面继承的类都能直接使用
     **/
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        //ButterKnife.bind(this, rootView);
        return View.inflate(mContext, setLayoutID(), null);
    }

    /**
     * Fragment 布局文件ID
     *
     * @return
     */
    protected abstract int setLayoutID();

    /**
     * Fragment中,onViewCreated()初始化View对象
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    /**
     * 初始化View对象
     * <p>
     * 如果在onCreateView()实现了ButterKnife.bind(),可不重写该方法
     *
     * @param rootView onViewCreated()传过来的rootView,用于rootView.findViewById()
     */
    protected void initView(View rootView) {

    }

    /**
     * 首次加载页面被调用
     */
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

    /**
     * Fragment使用show()/hide()方法会被调用
     *
     * @param hidden true隐藏 : false显示
     *               <p>
     *               Fragment生命周期跟随Activity
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    protected void showToast(String msg) {
        ToastUtils.showToast(mContext, msg);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, getClass().getSimpleName() + "onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, getClass().getSimpleName() + "onStart()");
    }
}
