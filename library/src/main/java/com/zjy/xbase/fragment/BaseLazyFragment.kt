package com.zjy.xbase.fragment

import androidx.viewbinding.ViewBinding

abstract class BaseLazyFragment<VB : ViewBinding> : BaseFragment<VB>() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInitData()
            lazyInitObservers()
            lazyInitListeners()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun lazyInitData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun lazyInitObservers()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun lazyInitListeners()

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initData() {

    }

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initObservers() {

    }

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initListeners() {

    }
}