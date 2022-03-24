package com.base.net;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.ssl.HttpsUtils;

/**
 * @author anhuang
 * @date 2022/3/24
 * Description : 初始化common 相关
 */
public class NetManager {
    private static NetManager NetManager;
    private Application applicationContext = null;
    private boolean debug = true;
    private static int DEFAULT_TIME_OUT = 30;

    public static NetManager getInstance() {
        if (NetManager == null) {
            synchronized (NetManager.class) {
                if (NetManager == null) {
                    NetManager = new NetManager();
                }
            }
        }
        return NetManager;
    }

    /**
     * 初始化配置  拿到AppContext
     *
     * @param application
     */
    public void initCommon(Application application) {
        if (application == null) {
            throw new RuntimeException("CommonLib init context is null");
        }
        applicationContext = application;
        initRxhttp();
    }

    /**
     * 初始化Rxhttp
     */
    private void initRxhttp() {
        RxHttpPlugins.init(getDefaultOkHttpClient())
                .setDebug(debug)
                //设置公共参数/请求头回调
                .setOnParamAssembly(null);
    }

    /**
     * 配置 okhttpClient
     * @return
     */
    private OkHttpClient getDefaultOkHttpClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                //添加信任证书
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //忽略host验证
                .hostnameVerifier((hostname, session) -> true)
                // todo 添加token 拦截器 用于处理token失效自动登录的问题
                //.addInterceptor()
                .build();
    }


    public Context getAppContext() {
        return applicationContext;
    }


    public NetManager setDebugMode(boolean debug) {
        this.debug = debug;
        return this;
    }
}
