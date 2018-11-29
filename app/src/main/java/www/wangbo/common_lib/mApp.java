package www.wangbo.common_lib;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.thefinestartist.utils.log.LogUtil;
import com.wb.baselib.BaseApplication;
import com.wb.baselib.appconfig.AppConfig;
import com.wb.baselib.appconfig.AppConfigManager;
import com.wb.baselib.http.HttpConfig;
import com.wb.baselib.log.LogTools;

import java.util.ArrayList;
import java.util.List;
public class mApp extends BaseApplication {
    @Override
    public String getRootPackAge() {
        return "dsfsdfsf";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        List<Integer> httpCodeOff = new ArrayList<>();
        httpCodeOff.add(201);
        AppConfig appConfig = new AppConfig.Bulider()
                .setMaxPage(10)
                .setHttpCodeOff(httpCodeOff)
                .bulider();
        AppConfigManager.newInstance().setAppConfig(appConfig);
        LogTools.setDebug(true);

        HttpConfig.HttpConfigBuilder httpConfig =
                new HttpConfig.HttpConfigBuilder()
                        .setmBaseUrl("http://test-px.huatu.com")
                        .setUseCustGson(true)
                        .setmIsUseLog(true);
        HttpConfig.newInstanceBuild(httpConfig);
    }
    /**
     * 初始化X5
     */
    private void initX5() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                LogUtil.i("onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                LogUtil.i("onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                LogUtil.i("onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                LogUtil.i("onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
