package com.zjy.sample.activity

import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mVM: MainVM by lazy {
        getViewModel(this, MainVM::class.java)
    }

    override fun initViewModel() {
        binding.mainVM = mVM
    }

    override fun initData() {
        //查询版本信息
        mVM.version()
    }

    override fun initObserver() {

    }

    override fun setListener() {

    }
}