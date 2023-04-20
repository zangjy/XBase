package com.zjy.xbase.net

/**
 * 文件名：RequestState
 * 创建者：ZangJiaYu
 * 创建日期：2023/4/20
 * 描述：请求状态的封装类
 */
sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>() // 请求开始，正在加载中
    class Success<out T>(val data: T) : RequestState<T>() // 请求成功，返回数据
    class Error(val throwable: Throwable) : RequestState<Nothing>() // 请求失败，返回错误信息
    object Complete : RequestState<Nothing>() // 请求完成，无论成功或失败

    /**
     * 根据当前状态映射不同的回调函数
     * @param loading 加载中的回调函数（可选）
     * @param success 成功的回调函数（可选）
     * @param error 失败的回调函数（可选）
     * @param complete 完成的回调函数（可选）
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