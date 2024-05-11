package com.zjy.xbase.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.xbase.net.BaseResp
import com.zjy.xbase.net.DoAsyncState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 执行一个网络请求，并将状态更新到MutableResult中
 * @receiver LifecycleOwner
 * @param mutableResult 接收状态的MutableResult对象
 * @param block 网络请求代码块
 * @return 返回一个Job对象，可以用于取消网络请求
 */
fun <T : BaseResp> LifecycleOwner.requestWithMutableResult(
    mutableResult: MutableResult<DoAsyncState<T>>,
    block: suspend () -> T,
): Job {
    return request(
        block = block,
        onLoading = { mutableResult.value = DoAsyncState.Loading },
        onSuccess = { mutableResult.value = DoAsyncState.Success(it) },
        onError = { mutableResult.value = DoAsyncState.Error(it) },
        onComplete = { mutableResult.value = DoAsyncState.Complete }
    )
}

/**
 * 执行一个网络请求
 * @receiver LifecycleOwner
 * @param block 网络请求代码块
 * @param onLoading 请求开始时的回调
 * @param onSuccess 请求成功时的回调
 * @param onError 请求失败时的回调
 * @param onComplete 请求完成时的回调
 * @param requiredState 回调的最低生命周期状态
 * @return 返回一个Job对象，可以用于取消网络请求
 */
fun <T : BaseResp> LifecycleOwner.request(
    block: suspend () -> T,
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit,
    onError: (throwable: Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    requiredState: Lifecycle.State = Lifecycle.State.STARTED,
): Job {
    return doAsync(block, onLoading, onSuccess = {
        val (state, exception) = it.paresResp()
        if (state) {
            onSuccess(it)
        } else {
            onError(exception)
        }
    }, onError, onComplete, requiredState)
}

/**
 * 执行一个异步任务，并将状态更新到MutableResult中
 * @receiver LifecycleOwner
 * @param mutableResult 接收状态的MutableResult对象
 * @param block 异步任务代码块
 * @return 返回一个Job对象，可以用于取消异步任务
 */
fun <T> LifecycleOwner.doAsyncWithMutableResult(
    mutableResult: MutableResult<DoAsyncState<T>>,
    block: suspend () -> T,
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
 * 执行一个异步任务
 * @receiver LifecycleOwner
 * @param block 异步任务代码块
 * @param onLoading 开始时的回调
 * @param onSuccess 成功时的回调
 * @param onError 失败时的回调
 * @param onComplete 完成时的回调
 * @param requiredState 回调的最低生命周期状态
 * @return 返回一个Job对象，可以用于取消异步任务
 */
fun <T> LifecycleOwner.doAsync(
    block: suspend () -> T,
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit,
    onError: (throwable: Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    requiredState: Lifecycle.State = Lifecycle.State.STARTED,
): Job {
    return lifecycleScope.launch {
        withContext(Dispatchers.Main) {
            onLoading()
        }

        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            withContext(Dispatchers.Main) {
                if (isLifecycleStateAtLeast(requiredState)) {
                    onSuccess(it)
                }
            }
        }.onFailure {
            withContext(Dispatchers.Main) {
                if (isLifecycleStateAtLeast(requiredState)) {
                    onError(it)
                }
            }
        }.also {
            withContext(Dispatchers.Main) {
                if (isLifecycleStateAtLeast(requiredState)) {
                    onComplete()
                }
            }
        }
    }
}

/**
 * 检查页面是否处于指定状态或更高状态
 * @receiver LifecycleOwner
 * @return 如果页面处于指定状态或更高状态返回true，否则为false
 */
private fun LifecycleOwner.isLifecycleStateAtLeast(requiredState: Lifecycle.State): Boolean {
    return lifecycle.currentState.isAtLeast(requiredState)
}