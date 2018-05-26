package xmx.zs.mvcframe.isolation.image;

import android.app.Activity;
import android.widget.ImageView;

/**
 * @author Lance
 * @date 2018/4/8
 */

public interface ImageLoader {


    /**
     * Show Image
     *
     * @param imageUrl
     * @param imageView
     */
    void displayImage(Activity activity, String imageUrl, ImageView imageView);

}
