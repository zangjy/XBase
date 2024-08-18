package com.zjy.sample.viewmodel

import com.zjy.sample.net.API
import com.zjy.xbase.net.RetrofitClient
import com.zjy.xbase.ui.intent.IUiIntent
import com.zjy.xbase.ui.state.IUiState
import com.zjy.xbase.viewmodel.BaseViewModel

/**
 * 文件名：BaseVM
 * 创建者：ZangJiaYu
 * 创建日期：2023/6/28
 * 描述：
 */
abstract class BaseVM<UiState : IUiState, UiIntent : IUiIntent> : BaseViewModel<UiState, UiIntent>() {

    companion object {
        val client: API by lazy {
            RetrofitClient.getService(API.BASE_URL, API::class.java)
        }
    }
}