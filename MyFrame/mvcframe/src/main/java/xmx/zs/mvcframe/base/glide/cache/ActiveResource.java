package xmx.zs.mvcframe.base.glide.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import xmx.zs.mvcframe.base.glide.cache.recycle.Resource;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/5/6 12:12
 * @本类描述	  活动资源
 * @内容说明  正在使用的图片资源
 *
 */
public class ActiveResource {

    //引用队列 (创建弱引用用到的)
    private       ReferenceQueue<Resource>  queue;
    private final Resource.ResourceListener resourceListener;
    //当前在使用的Bitmap资源(当前显示的图片)
    private Map<Key, ResourceWeakReference> activeResources = new HashMap<>();
    private Thread  cleanReferenceQueueThread;
    private boolean isShutdown;

    public ActiveResource(Resource.ResourceListener resourceListener) {
        this.resourceListener = resourceListener;
    }


    static final class ResourceWeakReference extends WeakReference<Resource> {

        final Key key;

        public ResourceWeakReference(Key key, Resource referent,
                                     ReferenceQueue<? super Resource> queue) {
            super(referent, queue);
            this.key = key;
        }
    }

    /**
     * 加入活动缓存
     *
     * @param key
     * @param resource
     */
    public void activate(Key key, Resource resource) {
        resource.setResourceListener(key, resourceListener);
        activeResources.put(key, new ResourceWeakReference(key, resource, getReferenceQueue()));
    }

    /**
     * 移除活动缓存
     */
    public Resource deactivate(Key key) {
        ResourceWeakReference reference = activeResources.remove(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }


    /**
     * 通过key,获得对应value
     *
     * @param key
     * @return
     */
    public Resource get(Key key) {
        ResourceWeakReference reference = activeResources.get(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    /**
     * 弱引用被回收了,会添加到ReferenceQueue引用队列
     * 被GC回收了才会触发这个方法
     * 这个方法让我们得到弱引用被回收,通知的作用
     *
     * @return
     */
    private ReferenceQueue<Resource> getReferenceQueue() {
        if (null == queue) {
            queue = new ReferenceQueue<>();
            cleanReferenceQueueThread = new Thread() {
                @Override
                public void run() {
                    while (!isShutdown) {
                        try {
                            //获得被回收掉的引用
                            ResourceWeakReference ref = (ResourceWeakReference) queue.remove();
                            activeResources.remove(ref.key);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            };
            cleanReferenceQueueThread.start();
        }
        return queue;
    }


    void shutdown() {
        isShutdown = true;
        if (cleanReferenceQueueThread != null) {
            cleanReferenceQueueThread.interrupt();
            try {
                //5s  必须结束掉线程
                cleanReferenceQueueThread.join(TimeUnit.SECONDS.toMillis(5));
                //超过5s 报异常提示
                if (cleanReferenceQueueThread.isAlive()) {
                    throw new RuntimeException("Failed to join in time");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
