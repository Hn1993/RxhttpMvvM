package com.base.rxhttp_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.base.common.base.BaseActivity
import com.base.common.base.BaseViewModel
import com.base.common.utils.Logger
import com.base.common.utils.PermissionUtils
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback
import com.base.rxhttp_mvvm.R
import com.base.rxhttp_mvvm.databinding.ActivityMainBinding
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.coroutines.launch

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
        })

        PermissionUtils.checkPermission(this, arrayListOf(Permission.ACCESS_COARSE_LOCATION)) {
            Logger.i("permission $it")
        }

    }

}