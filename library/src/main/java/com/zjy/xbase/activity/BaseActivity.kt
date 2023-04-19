package com.zjy.xbase.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    val binding: VB by lazy {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        initObserver()
        setListener()
        initFinished()
    }

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun initData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun initObserver()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun setListener()

    /**
     * 初始化完成
     */
    abstract fun initFinished()

    /**
     * 获取ViewModel
     * @param viewModelStoreOwner 作用域
     * @param modelClass ViewModel类
     */
    fun <T : ViewModel> getViewModel(
        viewModelStoreOwner: ViewModelStoreOwner,
        modelClass: Class<T>
    ): T {
        return ViewModelProvider(
            viewModelStoreOwner,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[modelClass]
    }
}