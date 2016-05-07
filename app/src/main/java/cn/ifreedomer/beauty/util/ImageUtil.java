package cn.ifreedomer.beauty.util;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:图片的处理类
 */
public class ImageUtil {
    public static void setFrescoImageView(String url, SimpleDraweeView draweeView){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                //构建
                .build();
        draweeView.setController(controller);
    }


}
