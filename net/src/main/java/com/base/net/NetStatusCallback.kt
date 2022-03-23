package com.base.net

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
open interface NetStatusCallback<T> {
    fun onRequestStart()
    fun onFailure(t: Throwable)
    fun onComplete(t: T)
}