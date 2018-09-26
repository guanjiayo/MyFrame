package zs.xmx.mvpframe.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/27 0:55
 * @本类描述	  不能滑动的ViewPager
 * @内容说明   ViewPager本身是能够去执行滑动操作：
 *             事件的处理   onTouchEvent()  return false;不处理事件
 *             不要去拦截事件  onIntercepTouchEvent() 处理拦截事件    return false 不拦截
 *
 */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public NoScrollViewPager(Context context) {
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


}
