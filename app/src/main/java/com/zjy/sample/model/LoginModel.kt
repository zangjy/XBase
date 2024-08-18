package com.zjy.sample.model

import com.google.gson.annotations.SerializedName

/**
 * 文件名：LoginModel
 * 创建者：ZangJiaYu
 * 创建日期：2024/8/18
 * 描述：
 */
data class LoginModel(
    @SerializedName("data")
    val `data`: String = "",
) : BaseModel()