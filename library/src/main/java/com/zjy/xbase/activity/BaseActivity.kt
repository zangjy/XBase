package com.zjy.xbase.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
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
        initObservers()
        initListeners()
    }

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun initData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun initObservers()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun initListeners()
}