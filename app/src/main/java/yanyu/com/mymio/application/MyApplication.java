package yanyu.com.mymio.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import yanyu.com.mymio.util.DisplayUtil;

/**
 * Created by zq on 2016/12/1 0001.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public static int statusHeight;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        statusHeight = DisplayUtil.getStatusBarHeight(this);
        //初始化facebook
        Fresco.initialize(this);
        //初始化okhttp
        initOkhttp();
    }

    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("mio"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

}
