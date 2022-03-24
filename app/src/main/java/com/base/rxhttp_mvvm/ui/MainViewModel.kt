package com.base.rxhttp_mvvm.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.base.common.base.BaseViewModel
import com.base.rxhttp_mvvm.ui.MainRepository
import kotlinx.coroutines.launch

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
class MainViewModel(application: Application) : BaseViewModel<MainRepository>(application){

    fun getData(){
        viewModelScope.launch {
            mRepository.getFriendList()
        }
    }
}