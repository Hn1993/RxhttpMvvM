package com.base.net

import android.util.Log
import rxhttp.wrapper.coroutines.Await
import okhttp3.OkHttpClient
import rxhttp.*

import rxhttp.wrapper.ssl.HttpsUtils

import rxhttp.wrapper.ssl.HttpsUtils.SSLParams
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


/**
 * @author anhuang
 * @date 2022/3/23
 * Description : base 暂时只做网络请求框架的初始化
 * 1.net 包只跟网络有关。 解析器，拦截器，公共请求头，都可以在这里设置，跟业务有关的代码在单独的repository编写
 */
open class BaseRepository {

    /**
     * 重写 await 方法加入失败监听   需要dialog的请求调用此方法
     */
    suspend fun <T> Await<T>.callbackAwait(callback: RequestCallback<Any>): T {
        onStart { callback.onRequestStart() }
            .awaitResult { callback.onComplete(it!!) }
            .onFailure { callback.onFailure(it) }
        return await()
    }

}