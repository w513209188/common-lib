package com.wb.baselib.base.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wb.baselib.R;
import com.wb.baselib.app.AppUtils;
import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.utils.ViewManager;

import java.util.HashMap;
import java.util.Map;
@Keep
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
//    protected BGASwipeBackHelper mSwipeBackHelper;
    private Dialog mDiaLog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        initSwipeBackFinish();
        if(!isAllImage()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setStatusBarColor(this.getResources().getColor(R.color.statusbar_color),0);
            }else {
                setStatusBarColor(this.getResources().getColor(R.color.statusbar_color_K),0);
            }
        }else {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        }
        setStatusBarTextColor();
        super.onCreate(savedInstanceState);
        ViewManager.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ExitApp");
        this.registerReceiver(this.broadcastReceiver, filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().finishActivity(this);
        try {
            this.unregisterReceiver(this.broadcastReceiver);
        } catch (Exception e) {
        }

    }

    protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    /**
     * 设置状态栏字体为黑色
     */
    public void setStatusBarTextColor(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 需要处理点击事件时，重写该方法
     *
     * @param v
     */
    public void onClick(View v) {
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * Md风格的对话框
     *
     * @param title
     * @param msg
     */
    public void showMdDialog(String title, String msg, String bt1, String bt2, MyDialogListener bul) {
        StyledDialog.buildMdAlert(title, msg, bul)
                .setBtnText(bt1, bt2)
                .show();
    }

    /**
     * 展示短的toast
     * @param msg
     */
    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示长的toast
     * @param msg
     */
    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 关闭打开的activity
     */
    public void AppAllExit() {
        Map<String,String> map=new HashMap<>();
        HttpConfig.newInstance().setmMapHeader(map);
        Intent intent = new Intent();
        intent.setAction("ExitApp");
        this.sendBroadcast(intent);
        super.finish();
    }

    /**
     * 显示加载对话框
     * @param msg
     */
    public void showLoadDiaLog(String msg) {
        mDiaLog = StyledDialog.buildLoading(msg).show();
    }

    /**
     * 隐藏加载对话框
     */
    public void hidLoadDiaLog() {
        if (mDiaLog == null)
            return;
        if (mDiaLog.isShowing()) {
            mDiaLog.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    public void addFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    public void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     *
     * @param fragment
     */
    public void removeFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
    public boolean isAllImage(){
        return false;
    }
}
