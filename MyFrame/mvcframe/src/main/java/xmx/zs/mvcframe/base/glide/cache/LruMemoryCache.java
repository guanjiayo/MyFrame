package xmx.zs.mvcframe.base.glide.cache;

import android.os.Build;
import android.support.v4.util.LruCache;

import xmx.zs.mvcframe.base.glide.cache.recycle.Resource;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/6 11:34
 * @本类描述	  内存缓存实现类
 * @内容说明   Lru算法处理内存缓存
 *
 * LruCache 简析:
 *
 * LruCache是对LinkedHashMap的一个封装方法,通过传一个MaxSize(可以缓存的内存大小)
 *
 */
public class LruMemoryCache extends LruCache<Key, Resource> implements MemoryCache {
    private ResourceRemoveListener listener;
    private boolean                isRemoved;

    public LruMemoryCache(int maxSize) {
        super(maxSize);
    }

    /**
     * LruCache的sizeOf()方法用来返回value占用的内存大小
     * <p>
     * 重写LruCache的sizeOf()获得计算返回对应value(Resource 就是 Bitmap)的内存大小
     */
    @Override
    protected int sizeOf(Key key, Resource value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果Bitmap复用内存的情况,有可能复用的内存比当前用到的内存多,这时需要通过getAllocationByteCount()函数获得准确的占用内存
            //当在4.4以上手机复用的时候 需要通过此函数获得准确的占用内存
            return value.getBitmap().getAllocationByteCount();
        }
        return value.getBitmap().getByteCount();
    }

    /**
     * LruCache里的entryRemoved()方法
     * 主要用于把oldValue(old Bitmap)内存移除
     * <p>
     * 这里我们不移除,把oldValue给复用池使用
     *
     * @param evicted
     * @param key
     * @param oldValue
     * @param newValue
     */
    @Override
    protected void entryRemoved(boolean evicted, Key key, Resource oldValue, Resource newValue) {
        //给复用池使用
        if (null != listener && null != oldValue && !isRemoved) {
            listener.onResourceRemoved(oldValue);
        }
    }

    /**
     * 用于区分主动移除内存缓存,还是被动移除缓存
     *
     * @param key
     * @return
     */
    @Override
    public Resource remove2(Key key) {
        // 如果是主动移除的不回调 listener.onResourceRemoved
        isRemoved = true;
        Resource remove = remove(key);
        isRemoved = false;
        return remove;
    }

    /**
     * 当Bitmap资源从内存被移除监听
     *
     * @param listener
     */
    @Override
    public void setResourceRemoveListener(ResourceRemoveListener listener) {
        this.listener = listener;
    }
}
