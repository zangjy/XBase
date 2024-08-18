package com.zjy.xbase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zjy.xbase.ui.intent.IUiIntent
import com.zjy.xbase.ui.state.IUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 文件名：BaseViewModel
 * 创建者：ZangJiaYu
 * 创建日期：2024/8/18
 * 描述：
 */
abstract class BaseViewModel<UiState : IUiState, UiIntent : IUiIntent> : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    private val _uiIntentFlow: Channel<UiIntent> = Channel(capacity = UNLIMITED)
    val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()

    abstract fun initUiState(): UiState

    fun sendUiState(uiState: UiState.() -> UiState) {
        _uiStateFlow.update { currentUiState -> uiState(currentUiState) }
    }

    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handlerIntent(it)
            }
        }
    }

    abstract fun handlerIntent(uiIntent: UiIntent)

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }
}