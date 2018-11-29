package com.wngbo.www.common_postphoto;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.wngbo.www.common_postphoto.common.ImageLoader;
import com.wngbo.www.common_postphoto.config.ISCameraConfig;
import com.wngbo.www.common_postphoto.config.ISListConfig;
import com.wngbo.www.common_postphoto.config.ISLookConfig;
import com.wngbo.www.common_postphoto.ui.ISCameraActivity;
import com.wngbo.www.common_postphoto.ui.ISListActivity;
import com.wngbo.www.common_postphoto.ui.ISLookPhotoActivity;

/**
 * 总线
 * <p>
 * Created by yuyuhang on 2017/10/23.
 */
public class ISNav {

    private static ISNav instance;

    private ImageLoader loader;

    public static ISNav getInstance() {
        if (instance == null) {
            synchronized (ISNav.class) {
                if (instance == null) {
                    instance = new ISNav();
                }
            }
        }
        return instance;
    }

    /**
     * 图片加载必须先初始化
     *
     * @param loader
     */
    public void init(@NonNull ImageLoader loader) {
        this.loader = loader;
    }

    public void displayImage(Context context, String path, ImageView imageView) {
        if (loader != null) {
            Log.e("---->displayImage>","yes");
            loader.displayImage(context, path, imageView);
        }else {
            Log.e("---->displayImage>","no");
        }
    }

    public void toListActivity(Object source, ISListConfig config, int reqCode) {
        if (source instanceof Activity) {
            ISListActivity.startForResult((Activity) source, config, reqCode);
        } else if (source instanceof Fragment) {
            ISListActivity.startForResult((Fragment) source, config, reqCode);
       }
    }

    public void toCameraActivity(Object source, ISCameraConfig config, int reqCode) {
        if (source instanceof Activity) {
            ISCameraActivity.startForResult((Activity) source, config, reqCode);
        } else if (source instanceof Fragment) {
            ISCameraActivity.startForResult((Fragment) source, config, reqCode);
        }
    }

    public void toLookPhotoActivity(Object source, ISLookConfig config) {
        if (source instanceof Activity) {
            ISLookPhotoActivity.startForResult((Activity) source, config);
        } else if (source instanceof Fragment) {
            ISLookPhotoActivity.startForResult((Fragment) source, config);
        }
    }

}
