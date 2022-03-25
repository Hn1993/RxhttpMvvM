package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.base.common.base.BaseActivity
import com.base.common.base.BaseViewModel
import com.base.common.utils.GlideUtils
import com.base.common.utils.Logger
import com.base.common.utils.PermissionUtils
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback
import com.base.rxhttp_mvvm.R
import com.base.rxhttp_mvvm.databinding.ActivityMainBinding
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        initData()
    }

    override fun initData() {
        viewModel.getData()

        viewModel.dataTest.observe(this,{
            binding.tv.text = it[0].link

            GlideUtils.loadImage("https://club7.s3-ap-southeast-1.amazonaws.com/16271886064950586681d-6402-4636-9501-65b5cacb8535.jpg",binding.image)
        })

        PermissionUtils.checkPermission(this, arrayListOf(Permission.ACCESS_COARSE_LOCATION)) {
            Logger.i("permission $it")
        }

    }

}