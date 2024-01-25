package com.zjy.sample.activity

import android.widget.Toast
import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity
import com.zjy.xbase.ext.getViewModel
import com.zjy.xbase.helper.MutableResultObserveHelper

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mVM: MainVM by lazy {
        getViewModel(this, MainVM::class.java)
    }

    override fun initData() {
        mVM.version()
    }

    override fun initObservers() {
        MutableResultObserveHelper(lifecycle, mVM.versionRequestState, { state ->
            state.map(success = {
                binding.tvDownLoadUrl.text = it.data
            }, error = {
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_SHORT).show()
            })
        }, MutableResultObserveHelper.ObserveType.TYPE_FOREVER)
    }

    override fun initListeners() {

    }
}