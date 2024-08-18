package com.zjy.sample.ui.state

import com.zjy.sample.model.LoginModel
import com.zjy.xbase.ui.state.IUiState

/**
 * 文件名：MainState
 * 创建者：ZangJiaYu
 * 创建日期：2024/8/18
 * 描述：
 */
data class MainState(val loginUIState: LoginUIState) : IUiState

sealed class LoginUIState {
    object INIT : LoginUIState()
    data class Success(val loginModel: LoginModel) : LoginUIState()
    data class Error(val throwable: Throwable) : LoginUIState()
}