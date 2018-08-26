package zs.xmx.mvpframe.isolation.proxy.image;

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

    /**
     * show Crop Image
     * @param imageUrl
     * @param transformation
     * @param imageView
     */
    void displayCropImage(Activity activity, String imageUrl, Object transformation, ImageView imageView);

}
