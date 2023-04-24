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
    // 默认的OkHttpClient实例
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 获取服务接口实例
     *
     * @param serviceClass 服务接口的类对象
     * @param baseUrl 服务接口的基础URL
     * @return 服务接口实例
     */
    fun <T> getService(serviceClass: Class<T>, baseUrl: String): T {
        return getService(client, serviceClass, baseUrl)
    }

    /**
     * 获取服务接口实例
     *
     * @param client OkHttpClient实例
     * @param serviceClass 服务接口的类对象
     * @param baseUrl 服务接口的基础URL
     * @return 服务接口实例
     */
    fun <T> getService(client: OkHttpClient, serviceClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
            .build()
            .create(serviceClass)
    }
}