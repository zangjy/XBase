package com.zjy.sample.fragment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hjq.gson.factory.GsonFactory
import com.zjy.sample.databinding.Fragment1Binding
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.fragment.BaseFragment
import com.zjy.xbase.helper.MutableResultObserveHelper

/**
 * 文件名：TestFragment
 * 创建者：ZangJiaYu
 * 创建日期：2024/5/11
 * 描述：
 */
@SuppressLint("SetTextI18n")
class Fragment1 : BaseFragment<Fragment1Binding>() {

    private val mVM: MainVM by activityViewModels()

    private val gson by lazy {
        GsonFactory.getSingletonGson()
    }

    override fun initObservers() {
        MutableResultObserveHelper(lifecycle, mVM.workbenchRequestState, { state ->
            state.map(success = {
                binding.tvDes.text = "Success方法回调：${gson.toJson(it)}"
            }, error = {
                binding.tvDes.text = "Error方法回调：${gson.toJson(it.message.toString())}"
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            })
        }, MutableResultObserveHelper.ObserveType.TYPE_FOREVER)
    }

    override fun initListeners() {
        binding.mbRequest.setOnClickListener {
            mVM.workbenchByRequest()
        }

        binding.mbDoSync.setOnClickListener {
            mVM.workbenchByDoAsync()
        }
    }

    override fun initData() {

    }
}