package zs.xmx.mvpframe.isolation.proxy.image.picasso;

import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import zs.xmx.mvpframe.isolation.proxy.image.ImageLoader;


/**
 * @author Lance
 * @date 2018/4/8
 */

public class PicassoImageLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, String imageUrl, ImageView imageView) {
        Picasso.get().load(imageUrl).into(imageView);
    }

    @Override
    public void displayCropImage(Activity activity, String imageUrl, Object transformation, ImageView imageView) {
        //todo 这里先放着,其他参数后面补充
        Picasso.get()
                .load(imageUrl)
                .transform((Transformation) transformation)
                //.placeholder(R.drawable.user_placeholder) //默认图片
                //.error(R.drawable.user_placeholder_error) //加载失败图片
                .into(imageView);
    }
}
