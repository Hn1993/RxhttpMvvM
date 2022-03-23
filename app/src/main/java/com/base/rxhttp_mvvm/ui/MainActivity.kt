package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.base.net.BaseRepository
import com.base.net.NetStatusCallback
import com.base.rxhttp_mvvm.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NetStatusCallback<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch { test() }
    }

    suspend fun test(){
        val mainRepository = MainRepository()
//        Log.e("ttt", mainRepository.getFriendList()?.get(0)?.toString().toString())
    }

    override fun onRequestStart() {
        TODO("Not yet implemented")
    }

    override fun onFailure(t: Throwable) {
        TODO("Not yet implemented")
    }


    override fun onComplete(t: Any) {
        TODO("Not yet implemented")
    }


}