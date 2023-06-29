package com.zjy.sample.activity

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity
import com.zjy.xbase.ext.getViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mVM: MainVM by lazy {
        getViewModel(this, MainVM::class.java)
    }

    override fun initData() {
        lifecycleScope.launch(Dispatchers.IO) {
            //查询版本
            mVM.version()
        }

//        doAsync({ BaseVM.client.version("40", "xiaohei") }, onSuccess = {
//            binding.tvDownLoadUrl.text = it.data
//        }, onError = {
//            Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_SHORT).show()
//        })
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