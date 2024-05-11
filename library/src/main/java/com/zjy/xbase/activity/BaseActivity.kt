package com.zjy.xbase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.zjy.xbase.ext.inflateBindingWithGeneric

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    val binding: VB by lazy {
        inflateBindingWithGeneric(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        initListeners()
        initData()
    }

    /**
     * 在该方法中初始化Observer
     */
    abstract fun initObservers()

    /**
     * 在该方法中设置点击事件
     */
    abstract fun initListeners()

    /**
     * 在该方法中初始化数据
     */
    abstract fun initData()
}