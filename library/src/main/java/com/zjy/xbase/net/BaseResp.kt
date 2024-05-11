package com.zjy.xbase.net

/**
 * 文件名：BaseResp
 * 创建者：ZangJiaYu
 * 创建日期：2024/5/11
 * 描述：
 */
open class BaseResp {
    open fun paresResp(): Pair<Boolean, Throwable> {
        return Pair(true, Throwable())
    }
}