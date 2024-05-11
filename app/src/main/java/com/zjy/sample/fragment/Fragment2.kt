package com.zjy.sample.fragment

import androidx.fragment.app.activityViewModels
import com.zjy.sample.databinding.Fragment2Binding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.fragment.BaseLazyFragment

/**
 * 文件名：Fragment2
 * 创建者：ZangJiaYu
 * 创建日期：2024/5/11
 * 描述：
 */
class Fragment2 : BaseLazyFragment<Fragment2Binding>() {

    private val mVM: MainVM by activityViewModels()

    override fun lazyInitObservers() {
        mVM.desValueChangeEvent.observe(this) {
            binding.tvDes.text = it
        }
    }

    override fun lazyInitListeners() {

    }

    override fun lazyInitData() {

    }
}