package com.zjy.sample.net

import com.zjy.sample.model.WorkbenchModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 文件名：API
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：API接口
 */
interface API {

    companion object {
        const val BASE_URL = "http://crmapi.sdymei.com/"
    }

    @GET("api/jingle/marketing/workbench")
    suspend fun workbench(
        @Query("token") token: String,
    ): WorkbenchModel
}