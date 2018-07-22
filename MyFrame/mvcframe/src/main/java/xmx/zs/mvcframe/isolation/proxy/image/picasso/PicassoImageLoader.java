package xmx.zs.mvcframe.isolation.proxy.image.picasso;

import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import xmx.zs.mvcframe.isolation.proxy.image.ImageLoader;


/**
 * @author Lance
 * @date 2018/4/8
 */

public class PicassoImageLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, String imageUrl, ImageView imageView) {
        Picasso.get().load(imageUrl).into(imageView);
    }
}
