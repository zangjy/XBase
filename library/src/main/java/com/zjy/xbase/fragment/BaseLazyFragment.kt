package com.zjy.xbase.fragment

import androidx.viewbinding.ViewBinding

abstract class BaseLazyFragment<VB : ViewBinding> : BaseFragment<VB>() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInitViewModel()
            lazyInitData()
            lazyInitObserver()
            lazySetListener()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    /**
     * 推荐在该方法中初始化ViewModel
     */
    abstract fun lazyInitViewModel()

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun lazyInitData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun lazyInitObserver()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun lazySetListener()

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initViewModel() {

    }

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initData() {

    }

    /**
     * 不要重写这个方法去做任何事
     */
    override fun initObserver() {

    }

    /**
     * 不要重写这个方法去做任何事
     */
    override fun setListener() {

    }
}