package zs.xmx.mvpframe.isolation.proxy.image.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import zs.xmx.mvpframe.isolation.proxy.image.ImageLoader;


/**
 * @author Lance
 * @date 2018/4/8
 */

public class GlideImageLoader implements ImageLoader {
    public GlideImageLoader() {
//        new GlideBuilder().setBitmapPool()
//        Glide.init(context, new GlideBuilder());
    }

    @Override
    public void displayImage(Activity activity, String imageUrl, ImageView view) {
        Glide.with(activity).load(imageUrl).into(view);
    }

    @Override
    public void displayCropImage(Activity activity, String imageUrl, Object transformation, ImageView imageView) {
        //todo 这里先放着,其他参数后面补充
        Glide.with(activity)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }


}
