package com.base.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.base.common.utils.ClassUtils
import com.base.net.BaseRepository

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
open class BaseViewModel<T : BaseRepository>(application: Application): AndroidViewModel(application) {

    @JvmField
    var mRepository: T

    init {
        mRepository = ClassUtils.getNewInstance(this, 0)
    }

    fun test(){}
}