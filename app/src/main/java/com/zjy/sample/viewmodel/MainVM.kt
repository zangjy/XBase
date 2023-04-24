package com.zjy.sample.viewmodel

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.sample.model.VersionModel
import com.zjy.sample.net.API
import com.zjy.xbase.ext.requestWithResult
import com.zjy.xbase.net.RequestState
import com.zjy.xbase.net.RetrofitClient

/**
 * 文件名：MainVM
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：
 */
class MainVM : ViewModel() {
    companion object {
        val client: API by lazy {
            RetrofitClient.getService("https://api.sdymei.com/", API::class.java)
        }
    }

    var versionRequestState = MutableResult<RequestState<VersionModel>>()

    /**
     * 查询版本
     * @return Job
     */
    fun version() = requestWithResult(versionRequestState) {
        client.version("40", "xiaohei")
    }
}