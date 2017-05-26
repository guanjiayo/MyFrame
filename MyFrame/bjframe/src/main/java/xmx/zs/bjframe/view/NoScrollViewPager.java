package xmx.zs.bjframe.view;

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
public class NoScrollViewPager extends LazyViewPager {

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //false 不能滑动
        return false;
    }
}
