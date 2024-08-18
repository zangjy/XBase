package com.zjy.sample.model

import com.google.gson.annotations.SerializedName
import com.zjy.xbase.net.BaseResp

/**
 * 文件名：BaseModel
 * 创建者：ZangJiaYu
 * 创建日期：2024/5/11
 * 描述：
 */
open class BaseModel(
    @SerializedName("errorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMsg")
    var errorMsg: String = "",
) : BaseResp() {
    override fun paresResp(): Pair<Boolean, Throwable> {
        return Pair(errorCode != -1, Throwable(errorMsg))
    }
}