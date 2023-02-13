package com.zjy.xbase.net

import com.hjq.gson.factory.GsonFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 文件名：RetrofitClient
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：网络请求
 */
object RetrofitClient {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun <T> getService(serviceClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
            .client(client)
            .build()
            .create(serviceClass)
    }
}