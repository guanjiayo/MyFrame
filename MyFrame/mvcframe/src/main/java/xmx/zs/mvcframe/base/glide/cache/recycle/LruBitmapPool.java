package xmx.zs.mvcframe.base.glide.cache.recycle;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.LruCache;

import java.util.NavigableMap;
import java.util.TreeMap;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/7 11:15
 * @本类描述	  Bitmap 复用池实现类
 * @内容说明   这里我们只实现了4.4以上的
 *
 * todo 有空补充更多的Android版本适配
 *
 */
public class LruBitmapPool extends LruCache<Integer, Bitmap> implements BitmapPool {

    // 负责筛选合适内存大小的集合
    NavigableMap<Integer, Integer> map = new TreeMap<>();
    //筛选出来的内存大小,最大超出倍数
    private final static int MAX_OVER_SIZE_MULTIPLE = 2;
    //区分主动回收内存,还是被动LrcCache移除
    private boolean isRemoved;

    public LruBitmapPool(int maxSize) {
        super(maxSize);
    }

    /**
     * 将Bitmap放入复用池
     *
     * @param bitmap
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void put(Bitmap bitmap) {
        //Bitmap 复用要满足一下几个条件
        // 1. Bitmap的inMutable需要为true。
        // 2. Android 4.4及以上只需要被复用的Bitmap的内存必须大于等于需要新获得Bitmap的内存，则允许复用此Bitmap。
        // 3. 4.4以下(3.0以上)则被复用的Bitmap与使用复用的Bitmap必须宽、高相等并且使用复用的Bitmap解码时设置的inSampleSize为1，才允许复用。

        //isMutable 必须weitrue
        if (!bitmap.isMutable()) {
            bitmap.recycle();
            return;
        }

        //这里我们只关注实现4.4以上的
        //todo 可参考这个类LruMemoryCache补全
        int size = bitmap.getAllocationByteCount();
        //如果当前存入的Bitmap比复用池的内存还大,我们就不存到复用池了
        if (size >= maxSize()) {
            bitmap.recycle();
            return;
        }

        put(size, bitmap);
        map.put(size, 0);
    }

    /**
     * 获得一个合适大小,可复用的Bitmap
     *
     * @param width
     * @param height
     * @param config
     * @return
     */
    @Override
    public Bitmap get(int width, int height, Bitmap.Config config) {
        //新Bitmap需要的内存大小  (这里只关心 argb8888和RGB565)
        //todo 后期可看情况补充
        int size = width * height * (config == Bitmap.Config.ARGB_8888 ? 4 : 2);
        //获取等于 size或者大于size的key
        Integer key = map.ceilingKey(size);
        //从key集合从找到一个>=size并且 <= size*MAX_OVER_SIZE_MULTIPLE
        if (null != key && key <= size * MAX_OVER_SIZE_MULTIPLE) {
            isRemoved = true;
            Bitmap remove = remove(key);
            isRemoved = false;
            return remove;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected int sizeOf(Integer key, Bitmap value) {
        return value.getAllocationByteCount();
    }

    @Override
    protected void entryRemoved(boolean evicted, Integer key, Bitmap oldValue, Bitmap newValue) {
        map.remove(key);
        if (!isRemoved) {
            oldValue.recycle();
        }
    }
}
