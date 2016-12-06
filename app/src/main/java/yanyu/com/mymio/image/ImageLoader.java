package yanyu.com.mymio.image;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import yanyu.com.mymio.application.MyApplication;
import yanyu.com.mymio.util.DisplayUtil;
import yanyu.com.mymio.util.MD5Utils;
import yanyu.com.mymio.util.StringUtil;

/**
 * Created by zq on 2016/8/16 0016.
 * Description: facebook 显示图片封装
 */
public class ImageLoader {

    private static ImageLoader imageLoader;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {

        if (imageLoader == null)
            imageLoader = new ImageLoader();
        return imageLoader;

    }

    /**
     * 显示Image
     *
     * @param mSimpleDraweeView
     */
    public void disPlayImage(SimpleDraweeView mSimpleDraweeView, String strUrl) {

        if (StringUtil.isEmpty(strUrl))
            return;
        String url = MD5Utils.geturl(strUrl);
        int width = 0;
        int heigth = 0;

        //如果layout里面没有设置宽高就给个默认高度
        width = mSimpleDraweeView.getWidth();
        heigth = mSimpleDraweeView.getHeight();

        if (width <= 0) {
            width = DisplayUtil.dip2px(MyApplication.getAppContext(), 40);
        }
        if (heigth <= 0) {
            heigth = DisplayUtil.dip2px(MyApplication.getAppContext(), 40);
        }

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .setResizeOptions(new ResizeOptions(width, heigth))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(mSimpleDraweeView.getController())
                .build();
        mSimpleDraweeView.setController(controller);
    }


}
