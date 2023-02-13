package com.zjy.sample.model

/**
 * 文件名：VersionRespBean
 * 创建者：ZangJiaYu
 * 创建日期：2022/4/11
 * 描述：查询版本信息返回实体
 */
data class VersionModel(
    var status: String,
    var message: String,
    var count: String,
    var data: String
)
