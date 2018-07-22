package xmx.zs.mvcframe.isolation.proxy.image;

import android.app.Activity;
import android.widget.ImageView;

/**
 * @author Lance
 * @date 2018/4/8
 */

public class ImageLoaderPresenter implements ImageLoader {
    //代理对象
    private ImageLoader imageLoader;
    private static ImageLoaderPresenter instance;

    public static ImageLoaderPresenter getInstance() {
        return instance;
    }

    private ImageLoaderPresenter(ImageLoader loader) {
        imageLoader = loader;
    }


    public static void init(ImageLoader loader) {
        if (null == instance) {
            synchronized (ImageLoaderPresenter.class) {
                if (null == instance) {
                    instance = new ImageLoaderPresenter(loader);
                }
            }
        }

    }

    @Override
    public void displayImage(Activity activity, String imageUrl, ImageView imageView) {
        imageLoader.displayImage(activity, imageUrl, imageView);
    }
}
