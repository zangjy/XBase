package com.zjy.xbase.net

/**
 * 文件名：DoAsyncState
 * 创建者：ZangJiaYu
 * 创建日期：2023/4/20
 * 描述：异步任务状态的密封类
 */
sealed class DoAsyncState<out T> {

    object Loading : DoAsyncState<Nothing>()
    class Success<out T>(val data: T) : DoAsyncState<T>()
    class Error(val throwable: Throwable) : DoAsyncState<Nothing>()
    object Complete : DoAsyncState<Nothing>()

    /**
     * 映射状态
     * @param loading 执行中
     * @param success 执行成功
     * @param error 执行失败
     * @param complete 执行完成
     */
    fun map(
        loading: (() -> Unit)? = null,
        success: ((T) -> Unit)? = null,
        error: ((Throwable) -> Unit)? = null,
        complete: (() -> Unit)? = null
    ) {
        when (this) {
            is Loading -> loading?.invoke()
            is Success -> success?.invoke(data)
            is Error -> error?.invoke(throwable)
            is Complete -> complete?.invoke()
        }
    }
}