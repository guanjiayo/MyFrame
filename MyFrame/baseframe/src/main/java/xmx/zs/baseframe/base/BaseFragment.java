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
 * @内容说明
 * @补充内容  1.添加/移除Fragment
 *
 * ---------------------------------     
 * @新增内容  //TODO 需要修改,Fragment用ADD方式添加,性能更优,onViewCreate initView
 *           //TODO 用数组管理Fragment,参考BaseFraagment2,修改
 *           //TODO Fragment添加使用add,多个Fragment的时候,先hide所有Fragment,再add默认的,其他Fragment当点击到才add(集合管理所有Fragment),当全部都add过,hide/show形式展示
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        //让子类覆盖
        return initView();
    }

    protected abstract View initView();


    /**
     * 跳转到新的Fragment
     * 说白了也是fragment的replace方法
     */
    protected void toOtherFragment(int layoutID, BaseFragment fragment) {
        if (null != fragment) {
            mContext.addFragment(layoutID, fragment);
        }
    }

    /**
     * 移除fragment
     */
    protected void removeFragment() {
        mContext.removeFragment();
    }

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
