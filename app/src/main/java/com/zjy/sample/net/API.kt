package com.zjy.sample.net

import com.zjy.sample.model.VersionModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 文件名：API
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：API接口
 */
interface API {
    /**
     * 查询版本信息
     */
    @GET("api/user/android/version")
    suspend fun version(
        @Query("code") code: String,
        @Query("companyId") companyId: String
    ): VersionModel
}