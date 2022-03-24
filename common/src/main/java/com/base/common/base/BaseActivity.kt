package com.base.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.base.common.utils.ToastUtils
import com.base.net.RequestCallback
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author：tqzhang on 18/3/12 19:22
 */

abstract class BaseActivity<VM : BaseViewModel<*>, DB : ViewBinding> : AppCompatActivity(),
    RequestCallback<Any> {

    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding()
        initStatusBar()
        initViews(savedInstanceState)
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
     * DataBinding or ViewBinding
     */
    private fun initViewDataBinding() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[1] as Class<*>
            when {
                ViewDataBinding::class.java.isAssignableFrom(cls) && cls != ViewDataBinding::class.java -> {
                    if (layoutId == 0) throw IllegalArgumentException("Using DataBinding requires overriding method layoutId")
                    binding = DataBindingUtil.setContentView(this, layoutId)
                    (binding as ViewDataBinding).lifecycleOwner = this
                }
                ViewBinding::class.java.isAssignableFrom(cls) && cls != ViewBinding::class.java -> {
                    cls.getDeclaredMethod("inflate", LayoutInflater::class.java).let {
                        @Suppress("UNCHECKED_CAST")
                        binding = it.invoke(null, layoutInflater) as DB
                        setContentView(binding.root)
                    }
                }
                else -> {
                    if (layoutId == 0) throw IllegalArgumentException("If you don't use ViewBinding, you need to override method layoutId")
                    setContentView(layoutId)
                }
            }
            createViewModel(type.actualTypeArguments[0])
        } else throw IllegalArgumentException("Generic error")
    }


    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel(type: Type) {
        val tClass = type as? Class<VM> ?: BaseViewModel::class.java
        viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
            .get(tClass) as VM
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