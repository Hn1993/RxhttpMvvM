package com.base.rxhttp_mvvm.ui

import android.app.Application

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
class App : Application() {
    private var app : Application? = null

    open fun getInstance() : Application? {
        return app
    }

    override fun onCreate() {
        super.onCreate()
        app = this

    }
}