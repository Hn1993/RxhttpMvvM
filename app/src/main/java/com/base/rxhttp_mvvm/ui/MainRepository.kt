package com.base.rxhttp_mvvm.ui

import android.util.Log
import com.base.net.BaseRepository
import com.base.rxhttp_mvvm.bean.Test
import rxhttp.tryAwait
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.RxHttpNoBodyParam
import rxhttp.wrapper.param.toResponse

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
open class MainRepository : BaseRepository() {

    suspend fun test() {
        //        var result:List<Test> =
        //            responseGet("https://www.wanandroid.com/friend/json",HashMap()) as List<Test>

        //        Log.e("text","result==="+ result?.get(0).category)

//            var result : RxHttpNoBodyParam = responseGet2("https://www.wanandroid.com/friend/json",HashMap()) as RxHttpNoBodyParam
//        var result2 = result.toResponse<List<Test>>().await()
//        Log.e("text","result2==="+ result2?.javaClass)

        var result: List<Test> =
            RxHttp.get("https://www.wanandroid.com/friend/json").toResponse<List<Test>>().await()
//        return RxHttp.get("https://www.wanandroid.com/friend/json").toResponse<List<Test>>().await()
//        Log.e("text","result==="+ result?.javaClass)
        Log.e("text", "result===" + result?.get(0).toString())
    }

    /**
     * 获取推荐的朋友列表
     */
    suspend fun getFriendList(): List<Test>? {
        return RxHttp.get("https://www.wanandroid.com/friend/json").toResponse<List<Test>>()
            .tryAwait()
    }
}