package com.zjy.sample.activity

import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity
import com.zjy.xbase.ext.getViewModel
import com.zjy.xbase.utils.ToastUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mVM: MainVM by lazy {
        getViewModel(this, MainVM::class.java)
    }

    override fun initData() {
        //查询版本
        mVM.version()
    }

    override fun initObservers() {
        mVM.versionRequestState.observe(this) { state ->
            state.map(success = {
                binding.tvDownLoadUrl.text = it.data
            }, error = {
                ToastUtils.showShort(it.message.toString())
            })
        }
    }

    override fun initListeners() {

    }
}