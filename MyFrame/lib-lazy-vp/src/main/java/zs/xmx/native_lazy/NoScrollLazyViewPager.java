package zs.xmx.native_lazy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/11/11 19:19
 * @本类描述	  NoScrollViewPager
 * @内容说明   根据自己需求写的不会滑动的LazyViewPager
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
public class NoScrollLazyViewPager extends LazyViewPager {

    public NoScrollLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollLazyViewPager(Context context) {
        this(context, null);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不拦击事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不处理事件
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);//不显示切换动画
    }
}
