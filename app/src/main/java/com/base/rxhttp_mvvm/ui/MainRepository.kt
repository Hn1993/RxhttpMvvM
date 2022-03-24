package com.base.rxhttp_mvvm.ui

import com.base.common.base.BaseViewModel
import com.base.net.BaseRepository
import com.base.net.RequestCallback
import com.base.rxhttp_mvvm.bean.Test
import rxhttp.async
import rxhttp.tryAwait
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse
import com.base.common.base.BaseActivity as BaseActivity

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
open class MainRepository : BaseRepository() {

    suspend fun test(activity: BaseActivity<*,*>) :List<Test>?{
        return RxHttp.get("https://www.wanandroid.com/friend/json").toResponse<List<Test>>().callbackAwait(activity)
    }


    /**
     * 获取推荐的朋友列表
     */
    suspend fun getFriendList() :List<Test>?{
        return RxHttp.get("https://www.wanandroid.com/friend/json").toResponse<List<Test>>().tryAwait()
    }


}


