package com.zjy.sample.viewmodel

import com.kunminx.architecture.domain.message.MutableResult
import com.zjy.sample.model.WorkbenchModel
import com.zjy.xbase.ext.doAsyncWithMutableResult
import com.zjy.xbase.ext.requestWithMutableResult
import com.zjy.xbase.net.DoAsyncState

/**
 * 文件名：MainVM
 * 创建者：ZangJiaYu
 * 创建日期：2022/3/20
 * 描述：
 */
class MainVM : BaseVM() {

    var workbenchRequestState = MutableResult<DoAsyncState<WorkbenchModel>>()

    var desValueChangeEvent = MutableResult<String>()

    fun workbenchByRequest() = requestWithMutableResult(workbenchRequestState) {
        client.workbench("111")
    }

    fun workbenchByDoAsync() = doAsyncWithMutableResult(workbenchRequestState) {
        client.workbench("111")
    }
}