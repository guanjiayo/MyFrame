package xmx.zs.mvcframe.base.glide.cache.recycle;

import android.graphics.Bitmap;

import xmx.zs.mvcframe.base.glide.cache.Key;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/6 11:30
 * @本类描述	  作为作为LinkedHashMap(硬盘缓存))的value值
 * @内容说明   这个类主要作用是通过acquire()和release(),通知Resource没有被使用的回调
 *
 */
public class Resource {
    private Bitmap bitmap;

    //引用计数(Bitmap被使用的次数)
    private int acquired;

    private ResourceListener listener;
    private Key              key;


    public Resource(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * 当acquired计数为0的时候 回调 onResourceReleased
     */
    public interface ResourceListener {
        void onResourceReleased(Key key, Resource resource);
    }

    public void setResourceListener(Key key, ResourceListener listener) {
        this.key = key;
        this.listener = listener;
    }

    /**
     * 释放资源
     */
    public void recycle() {
        if (acquired > 0) {
            return;
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }


    /**
     * acquired引用计数-1
     */
    public void release() {
        if (--acquired == 0) {
            listener.onResourceReleased(key, this);
        }
    }

    /**
     * acquired引用计数+1
     */
    public void acquire() {
        if (bitmap.isRecycled()) {
            throw new IllegalStateException("Acquire a recycled resource");
        }
        ++acquired;
    }

}
