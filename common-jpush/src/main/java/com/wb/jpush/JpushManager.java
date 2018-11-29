package com.wb.jpush;

import android.content.Context;
import com.wb.jpush.bean.ShapeType;
import com.wb.jpush.bean.ShareConfig;
import java.util.List;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jiguang.share.android.api.ShareParams;
import cn.jpush.android.api.JPushInterface;

/**
 * 极光管理器
 */
public class JpushManager {
    private static JpushManager jpushManager;
    public static JpushManager newInstance(){
        if(jpushManager==null){
            synchronized (JpushManager.class){
                jpushManager=new JpushManager();
            }
        }
        return jpushManager;
    }

    /**
     * 初始化推送
     * @param context
     * @param deBug 是否开启debug
     */
    public void initJpush(Context context,boolean deBug){
        JPushInterface.setDebugMode(deBug);
        JPushInterface.init(context);
    }

    /**
     *  初始化分享 授权
     * @param context
     * @param shareConfigs  分享配置信息 参考ShareConfig类
     * @param deBug 是否开启debug
     */
    public void initJpush(Context context, List<ShareConfig> shareConfigs, boolean deBug){
        JShareInterface.setDebugMode(deBug);
        PlatformConfig platformConfig=new PlatformConfig();
        for(ShareConfig shareConfig:shareConfigs){
            if(shareConfig.getShareType()==ShapeType.QQ){
                platformConfig.setQQ(shareConfig.getAppId(),shareConfig.getAppSecret());

            }else if(shareConfig.getShareType()==ShapeType.WX){
                platformConfig.setWechat(shareConfig.getAppId(),shareConfig.getAppSecret());
            }else if(shareConfig.getShareType()==ShapeType.SINA){
                platformConfig.setSinaWeibo(shareConfig.getAppId(),shareConfig.getAppSecret(),"");
            }
        }
        JShareInterface.init(context,platformConfig);
    }
    public void startShareTxt(int shareType,String txt){
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_TEXT);
        shareParams.setText(txt);
    }
}
