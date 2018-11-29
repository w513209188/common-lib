package com.wb.baselib.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wb.baselib.cache.GlideCatchUtil;
import com.wb.baselib.interfaces.GlideBitmapCall;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;

/**
 * 这个是glide处理工具类
 */

public class GlideManager {
    private static GlideManager glideUtils;
    public static GlideManager getInstance(){
        if(glideUtils==null){
            synchronized (GlideManager.class){
                glideUtils=new GlideManager();
            }
        }
        return glideUtils;
    }

    /**
     * 设置普通的图片
     * @param imageViewm
     * @param res  占位图
     * @param mContext
     * @param path 图片地址
     * @param isC 是否切元显示
     */
    public void setCommonPhoto(ImageView imageViewm, int res, Context mContext,String path,boolean isC){
        if(isC){
            Glide.with(mContext).load(path).centerCrop().placeholder(res).dontAnimate().error(res).into(imageViewm);
        }else {
            Glide.with(mContext).load(path).placeholder(res).dontAnimate().error(res).into(imageViewm);
        }

    }


    public void setGlideRoundTransImage(ImageView imageViewm, int res, Context mContext, String path){
        Glide.with(mContext).load(path).error(res).placeholder(res).transform(new GlideCircleTransform(mContext)).into(imageViewm);
    }

    /**
     * 实现圆角图片
     * @param imageViewm
     * @param res
     * @param mContext
     * @param path
     * @param dp 圆角的度数
     */
    public void setRoundPhoto(ImageView imageViewm, int res, Context mContext, String path,int dp){
        Glide.with(mContext).load(path).error(res).placeholder(res).transform(new GlideRoundTransform(mContext, dp)).into(imageViewm);
    }
    /**
     * 获取图片缓存大小
     * @param context
     * @return
     */
    public String getGlideCacherSize(Context context){
        try {
            return   GlideCatchUtil.getInstance(context).getCacheSize();
        }catch (Exception e){
            return "0kb";
        }
    }

    /**
     * 清除图片缓存
     * @param context
     * @return
     */
    public boolean clearCache(Context context){
        try {
            GlideCatchUtil.getInstance(context).cleanCatchDisk();
            GlideCatchUtil.getInstance(context).clearCacheDiskSelf();
            GlideCatchUtil.getInstance(context).clearCacheMemory();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 获取图片的 bitmap
     * @param context
     * @param path
     * @param glideBitmapCall
     */
    public void getImagBitmap(Context context, String path, final GlideBitmapCall glideBitmapCall){
        Glide.with(context).load(path).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                glideBitmapCall.getImagBitmap(resource);
            }
        });
    }

    public List<File> getJpegImage(final Context context, List<String> path) {
        List<File> files = new ArrayList<>();
        Flowable.just(path)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        // 同步方法直接返回压缩后的文件
                        return Luban.with(context).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(List<File> files) throws Exception {
                        files.addAll(files);
                    }
                });
        return files;
    }
}