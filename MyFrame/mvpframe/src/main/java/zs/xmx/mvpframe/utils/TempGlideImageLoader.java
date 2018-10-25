package zs.xmx.mvpframe.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by zxl on 2017/8/26.
 * todo 后面删掉,用自己的banner
 */

public class TempGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context)
                .load(path)
//                .placeholder(R.drawable.ic_image_default)
                .into(imageView);
    }
}
