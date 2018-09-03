package zs.xmx.mvpframe.weight

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.MotionEvent

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/2 23:28
 * @本类描述	  不能滑动的ViewPager
 * @内容说明  ViewPager本身是能够去执行滑动操作：
 *             事件的处理   onTouchEvent()  return false;不处理事件
 *             不要去拦截事件  onIntercepTouchEvent() 处理拦截事件    return false 不拦截

 *
 */
class NoScrollViewPager(context: Context) : ViewPager(context) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return false//不拦击事件
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false//不处理事件
    }

}