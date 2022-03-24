package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.base.common.base.BaseActivity
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback
import com.base.rxhttp_mvvm.R
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(){

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch { test() }
    }

    suspend fun test() {
        val mainRepository = MainRepository()
        mainRepository.test(this)
        Log.e("ttt", mainRepository.test(this)?.get(0)?.toString().toString())
    }




}