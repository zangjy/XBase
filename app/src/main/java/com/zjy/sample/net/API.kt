package com.zjy.sample.net

import com.zjy.sample.bean.LoginReqBean
import com.zjy.sample.model.LoginModel
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 文件名：API
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：API接口
 */
interface API {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    @POST("user/login")
    suspend fun login(@Body loginReqBean: LoginReqBean): LoginModel
}