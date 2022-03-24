package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.base.common.base.BaseActivity
import com.base.common.base.BaseViewModel
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback
import com.base.rxhttp_mvvm.R
import com.base.rxhttp_mvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        initData()
    }

    override fun initData() {
        viewModel.getData()
    }

}