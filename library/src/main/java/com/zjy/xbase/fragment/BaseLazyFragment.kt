package com.zjy.xbase.fragment

import androidx.viewbinding.ViewBinding

abstract class BaseLazyFragment<VB : ViewBinding> : BaseFragment<VB>() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()

        if (!isLoaded && !isHidden) {
            lazyInitObservers()
            lazyInitListeners()
            lazyInitData()

            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        isLoaded = false
    }

    /**
     * 在该方法中初始化Observer
     */
    abstract fun lazyInitObservers()

    /**
     * 在该方法中设置点击事件
     */
    abstract fun lazyInitListeners()

    /**
     * 在该方法中初始化数据
     */
    abstract fun lazyInitData()

    final override fun initObservers() {}

    final override fun initListeners() {}

    final override fun initData() {}
}