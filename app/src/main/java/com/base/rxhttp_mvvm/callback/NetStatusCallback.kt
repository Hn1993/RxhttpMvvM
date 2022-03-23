package com.base.rxhttp_mvvm.callback

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
open interface Callback<T> {
    fun onStart()
    fun onFailure(t: Throwable)
    fun onComplete(t: T)
}