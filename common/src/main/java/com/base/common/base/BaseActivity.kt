package com.base.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback

/**
 * @author：tqzhang on 18/3/12 19:22
 */
abstract class BaseActivity<T : AndroidViewModel, binding : ViewDataBinding> : AppCompatActivity(),
    RequestCallback<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //状态栏
        initStatusBar()
        //设置布局内容
        setContentView(layoutId)
        //初始化控件
        initViews(savedInstanceState)
        //初始化ToolBar
        initToolBar()
    }

    /**
     *
     */
    protected fun onStateRefresh() {}
    protected fun initStatusBar() {}

    /**
     * 设置布局layout
     *
     * @return
     */
    abstract val layoutId: Int

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    abstract fun initViews(savedInstanceState: Bundle?)

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    abstract fun initData()

    /**
     * 初始化toolbar
     */
    protected fun initToolBar() {
        //doSomething
    }

    /**
     * 显示进度条
     */
    protected fun showLoading() {}

    /**
     * 隐藏进度条
     */
    protected fun hideLoading() {}

    /**
     * 网络请求开始
     */
    override fun onRequestStart() {
        Log.e("text", "+ onRequestStart===")
        ToastUtils.show("onRequestStart")
        showLoading()
    }

    /**
     * 失败
     */
    override fun onFailure(t: Throwable) {
        Log.e("text", "+ onFailure===")
        hideLoading()
    }

    /**
     * 网络请求完成
     */
    override fun onComplete(t: Any) {
        Log.e("text", "+ onComplete===")
        hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}