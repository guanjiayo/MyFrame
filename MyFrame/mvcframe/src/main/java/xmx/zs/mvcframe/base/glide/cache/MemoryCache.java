package xmx.zs.mvcframe.base.glide.cache;

import xmx.zs.mvcframe.base.glide.cache.recycle.Resource;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/6 11:33
 * @本类描述	  内存缓存
 * @内容说明
 *
 */
public interface MemoryCache {

    interface ResourceRemoveListener {
        void onResourceRemoved(Resource resource);
    }

    Resource put(Key key, Resource resource);

    void setResourceRemoveListener(ResourceRemoveListener listener);

    Resource remove2(Key key);
}
