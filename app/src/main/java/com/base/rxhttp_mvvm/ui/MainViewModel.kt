package com.base.rxhttp_mvvm.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.common.base.BaseViewModel
import com.base.common.utils.Logger
import com.base.rxhttp_mvvm.bean.Test
import com.base.rxhttp_mvvm.ui.MainRepository
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.launch

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
class MainViewModel(application: Application) : BaseViewModel<MainRepository>(application){

    var dataTest = UnPeekLiveData<List<Test>>()

    fun getData(){
        viewModelScope.launch {
            var data: List<Test>? =  mRepository.getFriendList()
            if (data != null){
                dataTest.postValue(data)
            }
            Logger.i(data.toString())
        }
    }
}