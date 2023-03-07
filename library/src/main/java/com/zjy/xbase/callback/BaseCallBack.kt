package com.zjy.xbase.callback

/**
 * 文件名：BaseCallBack
 * 创建者：ZangJiaYu
 * 创建日期：2023/2/13
 * 描述：
 */
data class BaseCallBack<T>(val isSuccess: Boolean, val message: String = "", val data: T)