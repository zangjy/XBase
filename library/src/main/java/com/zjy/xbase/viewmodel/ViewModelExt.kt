package com.zjy.xbase.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.xbase.net.RequestState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 在Activity中获取ViewModel实例
 * @receiver AppCompatActivity
 * @param viewModelStoreOwner 作用域
 * @param modelClass ViewModel类
 * @return ViewModel实例
 */
fun <T : ViewModel> AppCompatActivity.getViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    modelClass: Class<T>
): T {
    return ViewModelProvider(viewModelStoreOwner)[modelClass]
}

/**
 * 在Fragment中获取ViewModel实例
 * @receiver Fragment
 * @param viewModelStoreOwner 作用域
 * @param modelClass ViewModel类
 * @return ViewModel实例
 */
fun <T : ViewModel> Fragment.getViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    modelClass: Class<T>
): T {
    return ViewModelProvider(viewModelStoreOwner)[modelClass]
}

/**
 * 网络请求，使用协程进行异步处理，通过 [result] 回调处理请求结果，包括请求状态 [RequestState] 和请求结果 [T]
 * @receiver ViewModel
 * @param result 请求结果回调函数，参数为 [RequestState]，包括请求状态 [RequestState] 和请求结果 [T]
 * @param block 请求代码块，需要使用 `suspend` 修饰
 * @return Job  [Job]对象，用于取消请求
 */
fun <T> ViewModel.requestWithResult(
    result: MutableResult<RequestState<T>>,
    block: suspend () -> T
): Job {
    result.value = RequestState.Loading
    return request(
        block = block,
        onSuccess = { result.value = RequestState.Success(it) },
        onError = { result.value = RequestState.Error(it) },
        onComplete = { result.value = RequestState.Complete }
    )
}

/**
 * 网络请求，使用协程进行异步处理，通过 [onSuccess]、[onError]回调处理请求结果，[onComplete]回调处理请求完成事件
 * @receiver ViewModel
 * @param block 请求代码块，需要使用 `suspend` 修饰
 * @param onSuccess 请求成功回调函数，参数为请求结果 [T]
 * @param onError 请求失败回调函数，参数为 [Throwable]，即请求失败的异常信息
 * @param onComplete 请求完成回调函数
 * @return Job [Job]对象，用于取消请求
 */
fun <T> ViewModel.request(
    block: suspend () -> T,
    onSuccess: (T) -> Unit,
    onError: (throwable: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            block()
        }.onSuccess {
            onSuccess(it)
        }.onFailure {
            onError(it)
        }.also {
            onComplete()
        }
    }
}