package com.zjy.sample.fragment

import android.annotation.SuppressLint
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.hjq.gson.factory.GsonFactory
import com.zjy.sample.databinding.Fragment1Binding
import com.zjy.sample.ui.intent.MainIntent
import com.zjy.sample.ui.state.LoginUIState
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.fragment.BaseFragment
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            mVM.uiStateFlow.map { it.loginUIState }.collect { loginUIState ->
                when (loginUIState) {
                    is LoginUIState.Success -> {
                        binding.tvDes.text =
                            "Success回调：\n${gson.toJson(loginUIState.loginModel)}"
                    }

                    is LoginUIState.Error -> {
                        binding.tvDes.text =
                            "Error回调：\n${loginUIState.throwable.message}"
                    }

                    else -> {}
                }
            }
        }
    }

    override fun initListeners() {
        binding.mbRequest.setOnClickListener {
            mVM.sendUiIntent(MainIntent.LoginByRequest)
        }

        binding.mbDoSync.setOnClickListener {
            mVM.sendUiIntent(MainIntent.LoginByDoAsync)
        }
    }

    override fun initData() {

    }
}