package xmx.zs.mvcframe.base.glide.cache;

import java.security.MessageDigest;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/6 11:31
 * @本类描述	  作为LinkedHashMap(硬盘缓存))的key值
 * @内容说明
 *
 */
public interface Key {
    void updateDiskCacheKey(MessageDigest md);
}
