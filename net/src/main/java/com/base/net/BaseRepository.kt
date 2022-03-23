package com.base.net

import android.util.Log
import okhttp3.Response
import rxhttp.wrapper.coroutines.Await
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse
import okhttp3.OkHttpClient
import rxhttp.*

import rxhttp.wrapper.ssl.HttpsUtils

import rxhttp.wrapper.ssl.HttpsUtils.SSLParams
import java.lang.reflect.Type
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


/**
 * @author anhuang
 * @date 2022/3/23
 * Description : base 暂时只做网络请求框架的初始化
 * 1.net 包只跟网络有关。 解析器，拦截器，公共请求头，都可以在这里设置，跟业务有关的代码在单独的repository编写
 */
open class BaseRepository {

    companion object{
        var inited = false
    }


    constructor(){
        initRxHttp()
    }

    /**
     * rxhttp 初始化
     */
    private fun initRxHttp(){
        Log.e("text", "initRxHttp--$inited")
        if (inited) return
        RxHttpPlugins.init(OkHttpClient())      //自定义OkHttpClient对象
            .setDebug(true)
        inited = true
    }

    /**
     * 重写 await 方法加入失败监听
     */
    suspend fun <T> Await<T>.callbackAwait(callback: NetStatusCallback<T>){
        onStart { callback.onRequestStart() }
            .awaitResult { callback.onComplete(it) }
            .onFailure { callback.onFailure(it) }
    }


//    interface Callback<T> {
//        fun onStart()
//        fun onFailure(t: Throwable)
//        fun onComplete(t: T)
//    }



//    /**
//     * get 请求
//     */
//    suspend fun <T : Any> responseGet(url: String, params: HashMap<String, Any>) : List<T>? {
//
//        RxHttpPlugins.init(OkHttpClient())      //自定义OkHttpClient对象
//            .setDebug(true)
//        return RxHttp.get(url).addAll(params).toResponse<List<T>>().tryAwait()
//    }
//
    /**
     * get 请求
     */
//    suspend fun responseGet2(url: String, params: HashMap<String, Any>) : Any? {
//
//        RxHttpPlugins.init(OkHttpClient())      //自定义OkHttpClient对象
//            .setDebug(true)
//
//        return RxHttp.get(url).addAll(params).toResponse<List<Test>>().await()
//    }

}