package com.zjy.sample.viewmodel

import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.sample.model.VersionModel
import com.zjy.xbase.ext.doAsyncWithMutableResult
import com.zjy.xbase.net.DoAsyncState

/**
 * 文件名：MainVM
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：
 */
class MainVM : BaseVM() {
    var versionRequestState = MutableResult<DoAsyncState<VersionModel>>()

    /**
     * 查询版本
     * @return Job
     */
    fun version() = doAsyncWithMutableResult(versionRequestState) {
        client.version("40", "xiaohei")
    }
}