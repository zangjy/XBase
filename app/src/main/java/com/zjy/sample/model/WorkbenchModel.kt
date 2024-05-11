package com.zjy.sample.model

import com.google.gson.annotations.SerializedName

/**
 * 文件名：WorkbenchModel
 * 创建者：ZangJiaYu
 * 创建日期：2022/4/11
 * 描述：
 */
data class WorkbenchModel(
    @SerializedName("data")
    var data: String,
) : BaseModel()
