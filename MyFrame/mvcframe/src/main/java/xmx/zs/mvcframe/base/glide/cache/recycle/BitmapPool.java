package xmx.zs.mvcframe.base.glide.cache.recycle;

import android.graphics.Bitmap;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/7 11:02
 * @本类描述	  Bitmap复用池
 * @内容说明
 *
 */
public interface BitmapPool {

    void put(Bitmap bitmap);

    /**
     * 获得一个可复用的Bitmap
     * <p>
     * 三个参数计算出内存大小
     *
     * @param width
     * @param height
     * @param config
     * @return
     */
    Bitmap get(int width, int height, Bitmap.Config config);
}
