package com.zjy.sample.viewmodel

import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.sample.bean.LoginReqBean
import com.zjy.sample.ui.intent.MainIntent
import com.zjy.sample.ui.state.LoginUIState
import com.zjy.sample.ui.state.MainState
import com.zjy.xbase.ext.doAsync
import com.zjy.xbase.ext.request

/**
 * 文件名：MainVM
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：
 */
class MainVM : BaseVM<MainState, MainIntent>() {

    var desValueChangeEvent = MutableResult<String>()

    override fun initUiState(): MainState {
        return MainState(LoginUIState.INIT)
    }

    override fun handlerIntent(uiIntent: MainIntent) {
        when (uiIntent) {
            is MainIntent.LoginByRequest -> {
                request({
                    client.login(LoginReqBean("aa", "123"))
                }, onSuccess = { loginModel ->
                    sendUiState {
                        copy(loginUIState = LoginUIState.Success(loginModel))
                    }
                }, onError = { throwable ->
                    sendUiState {
                        copy(loginUIState = LoginUIState.Error(throwable))
                    }
                })
            }

            is MainIntent.LoginByDoAsync -> {
                doAsync({
                    client.login(LoginReqBean("aa", "123"))
                }, onSuccess = { loginModel ->
                    sendUiState {
                        copy(loginUIState = LoginUIState.Success(loginModel))
                    }
                }, onError = { throwable ->
                    sendUiState {
                        copy(loginUIState = LoginUIState.Error(throwable))
                    }
                })
            }
        }
    }
}