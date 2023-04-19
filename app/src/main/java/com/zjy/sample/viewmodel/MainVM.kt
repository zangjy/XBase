package com.zjy.sample.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zjy.xbase.net.RetrofitClient
import com.zjy.sample.model.VersionModel
import com.zjy.sample.net.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 文件名：MainVM
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：
 */
class MainVM() : ViewModel() {

    private val client = RetrofitClient.getService("https://api.sdymei.com/", API::class.java)

    var versionModel = ObservableField<VersionModel>()

    fun version() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                versionModel.set(client.version("40", "xiaohei"))
            } catch (e: Exception) {

            }
        }
    }
}