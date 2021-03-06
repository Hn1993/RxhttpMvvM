package com.base.net

/**
 * @author anhuang
 * @date 2022/3/23
 * Description : 网络请求监听的回调
 */
open interface RequestCallback<T> {
    fun onRequestStart()
    fun onFailure(t: Throwable)
    fun onComplete(t: T)
}