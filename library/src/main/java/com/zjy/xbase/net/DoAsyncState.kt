package com.zjy.xbase.net

/**
 * 文件名：DoAsyncState
 * 创建者：ZangJiaYu
 * 创建日期：2023/4/20
 * 描述：异步操作状态的封装类
 */
sealed class DoAsyncState<out T> {
    object Loading : DoAsyncState<Nothing>() //操作正在进行或加载中
    class Success<out T>(val data: T) : DoAsyncState<T>() //操作成功
    class Error(val throwable: Throwable) : DoAsyncState<Nothing>() //操作失败
    object Complete : DoAsyncState<Nothing>() //操作完成

    /**
     * 根据当前状态映射不同的回调函数
     * @param loading 操作正在进行或加载中的回调函数（可选）
     * @param success 操作成功的回调函数（可选）
     * @param error 操作失败的回调函数（可选）
     * @param complete 操作完成的回调函数（可选）
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