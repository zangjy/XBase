package com.zjy.sample.viewmodel

import androidx.lifecycle.ViewModel
import com.zjy.sample.net.API
import com.zjy.xbase.net.RetrofitClient

/**
 * 文件名：BaseVM
 * 创建者：ZangJiaYu
 * 创建日期：2023/6/28
 * 描述：
 */
open class BaseVM : ViewModel() {
    companion object {
        val client: API by lazy {
            RetrofitClient.getService(API.BASE_URL, API::class.java)
        }
    }
}