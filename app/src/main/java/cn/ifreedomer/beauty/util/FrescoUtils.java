package cn.ifreedomer.beauty.util;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:一些fresco的方法
 */
public class FrescoUtils {
    public static void setImageUrl(String imageUrl, SimpleDraweeView draweeView){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)
                .build();
        draweeView.setController(controller);
    }
}
