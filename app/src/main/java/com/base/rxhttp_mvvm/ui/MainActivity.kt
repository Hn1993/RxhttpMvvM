package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.base.rxhttp_mvvm.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
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
        Log.e("ttt", mainRepository.getFriendList()?.get(0)?.toString().toString())
    }
}