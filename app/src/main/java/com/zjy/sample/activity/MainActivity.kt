package com.zjy.sample.activity

import android.widget.Toast
import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity
import com.zjy.xbase.ext.getViewModel

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
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun initListeners() {

    }
}