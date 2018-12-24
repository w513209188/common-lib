package www.wangbo.common_lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.base.fragment.LazyFragment;
import com.wb.baselib.bean.Result;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.http.HttpManager;
import com.wb.baselib.http.exception.ApiException;
import com.wb.baselib.http.observer.BaseObserver;
import com.wb.baselib.image.GlideManager;
import com.wb.baselib.log.LogTools;
import com.wngbo.www.common_postphoto.ISNav;
import com.wngbo.www.common_postphoto.common.ImageLoader;
import com.wngbo.www.common_postphoto.config.ISListConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import www.wangbo.common_lib.R;

public class MainActivity extends AppCompatActivity {
    private Button course_wb;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        course_wb=this.findViewById(R.id.webView);
    }
}
