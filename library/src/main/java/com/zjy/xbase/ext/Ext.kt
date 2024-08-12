package com.zjy.xbase.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * 尝试执行 [block] 代码块，如果成功则返回 [Result.success]，否则返回 [Result.Failure]
 * @param block 要执行的代码块
 * @return [Result] 对象，包含成功或失败
 */
suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (e: Throwable) {
    Result.failure(e)
}

/**
 * 如果当前协程作用域仍然有效，则在主线程上执行 [block] 代码块
 * @receiver CoroutineScope
 * @param block 要执行的代码块
 */
suspend fun CoroutineScope.runOnMainIfActive(block: suspend () -> Unit) {
    if (isActive) {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}