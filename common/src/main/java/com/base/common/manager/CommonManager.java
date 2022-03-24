package com.base.common.manager;

import android.app.Application;
import android.content.Context;

/**
 * @author anhuang
 * @date 2022/3/24
 * Description : 初始化common 相关
 */
public class CommonManager {
    private static CommonManager commonManager;
    private Application applicationContext = null;
    private boolean debug = false;

    public static CommonManager getInstance() {
        if (commonManager == null) {
            synchronized (CommonManager.class) {
                if (commonManager == null) {
                    commonManager = new CommonManager();
                }
            }
        }
        return commonManager;
    }

    /**
     * 初始化配置  拿到AppContext
     * @param application
     */
    public void initCommon(Application application){
        if (application == null){
            throw new RuntimeException("CommonLib init context is null");
        }
        applicationContext = application;
    }


    public Context getAppContext() {
        return applicationContext;
    }


    public CommonManager setDebugMode(boolean debug){
        this.debug = debug;
        return this;
    }
}
