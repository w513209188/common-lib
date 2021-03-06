package www.wangbo.common_lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.wangbo.smartrefresh.header.RefreshUtils;
import com.wb.baselib.adapter.ViewPageAdapter;
import com.wb.baselib.adapter.ViewPageTabAdapter;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.base.activity.BaseActivity;
import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.base.fragment.LazyFragment;
import com.wb.baselib.bean.Result;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.http.HttpManager;
import com.wb.baselib.http.exception.ApiException;
import com.wb.baselib.http.observer.BaseObserver;
import com.wb.baselib.image.GlideManager;
import com.wb.baselib.log.LogTools;
import com.wb.baselib.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jiguang.share.android.api.ShareParams;
import io.reactivex.disposables.Disposable;
import cn.jiguang.share.android.api.Platform;
import www.wangbo.common_lib.R;

public class MainActivity extends BaseActivity {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private ViewPageAdapter mPagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidingTabLayout = this.findViewById(R.id.stl_home_tabs);
        mViewPager = this.findViewById(R.id.vp_home_pages);
        List<BaseFragment> baseFragments=new ArrayList<>();
        List<String> strings=new ArrayList<>();
        strings.add("你猜1");
        strings.add("你猜2");
        baseFragments.add(new TestFragment());
        baseFragments.add(new TestFragment());
        mPagerAdapter=new ViewPageAdapter(getSupportFragmentManager(),baseFragments,strings);
        mViewPager.setAdapter(mPagerAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);


//        ShareParams shareParams = new ShareParams();
//        shareParams.setShareType(Platform.SHARE_MINI_PROGRAM);
//        shareParams.setTitle(share_title);
//        shareParams.setText(share_text);
//        shareParams.setUrl(share_url);//用于微信低版本兼容，当微信客户端为低版本时，小程序分享变网页分享
//        shareParams.setImagePath(file.getAbsolutePath());
//        shareParams.setMiniProgramWithShareTicket(false);//
//        shareParams.setMiniProgramType(0);// 正式版:0，开发版（测试版）:1，体验版（预览版）:2
//        shareParams.setMiniProgramImagePath(file.getAbsolutePath());//小程序图片地址
//        shareParams.setMiniProgramPath("pages/index/index");//小程序页面路径
//        shareParams.setMiniProgramUserName("gh_cd370c00d3d4");//小程序原始id


    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
