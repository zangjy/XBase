package com.zjy.xbase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 发起网络请求
 * @param block 网络请求代码块
 * @param successCallBack 成功时回调，返回值为网络请求代码块返回值
 * @param failedCallBack 错误时的回调，包括网络异常、解析数据异常等
 * @return Job
 */
fun <T> ViewModel.request(
    block: suspend () -> T,
    successCallBack: (T) -> Unit,
    failedCallBack: (throwable: Throwable) -> Unit
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            block()
        }.onSuccess {
            successCallBack(it)
        }.onFailure {
            failedCallBack(it)
        }
    }
}