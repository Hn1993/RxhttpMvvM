package com.base.rxhttp_mvvm.ui

import android.app.Application
import com.base.common.manager.CommonManager
import com.base.net.NetManager

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
class App : Application() {
    private var app: Application? = null

    open fun getInstance(): Application? {
        return app
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        // 初始化一些公共的 组件
        CommonManager.getInstance().setDebugMode(false).initCommon(app)
        NetManager.getInstance().initCommon(app)
    }
}