package com.zjy.xbase.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.xbase.net.DoAsyncState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 执行简化的异步操作，将结果更新到MutableResult中
 *
 * @receiver ViewModel
 * @param mutableResult 结果对象，用于存储异步操作的结果
 * @param block 异步操作的逻辑
 * @return 返回一个Job对象，可以用于取消异步任务
 */
fun <T> ViewModel.doAsyncWithMutableResult(
    mutableResult: MutableResult<DoAsyncState<T>>,
    block: suspend () -> T
): Job {
    return doAsync(
        block = block,
        onLoading = { mutableResult.value = DoAsyncState.Loading },
        onSuccess = { mutableResult.value = DoAsyncState.Success(it) },
        onError = { mutableResult.value = DoAsyncState.Error(it) },
        onComplete = { mutableResult.value = DoAsyncState.Complete }
    )
}

/**
 * 执行异步操作，并提供回调函数来处理不同的结果
 *
 * @receiver ViewModel
 * @param block 异步操作的逻辑
 * @param onLoading 操作正在进行或加载时的回调函数
 * @param onSuccess 成功时的回调函数，参数为异步操作的结果
 * @param onError 失败时的回调函数，参数为异常对象
 * @param onComplete 完成时的回调函数
 * @return 返回一个Job对象，可以用于取消异步任务
 */
fun <T> ViewModel.doAsync(
    block: suspend () -> T,
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit,
    onError: (throwable: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
): Job {
    return viewModelScope.launch {
        withContext(Dispatchers.Main) {
            onLoading()
        }
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            withContext(Dispatchers.Main) {
                onSuccess(it)
            }
        }.onFailure {
            withContext(Dispatchers.Main) {
                onError(it)
            }
        }.also {
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }
}