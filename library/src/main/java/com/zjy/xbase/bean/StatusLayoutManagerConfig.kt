package com.zjy.xbase.bean

/**
 * 文件名：StatusLayoutManagerConfig
 * 创建者：ZangJiaYu
 * 创建日期：2022/5/11
 * 描述：切换状态布局的配置
 */
data class StatusLayoutManagerConfig(
    var defaultLoadingText: String,//加载中布局的提示文本

    var defaultEmptyText: String,//空布局中的提示文本
    var defaultEmptyImg: Int,//空布局的默认图片
    var defaultEmptyRetryText: String,//空布局的重试文本
    var defaultEmptyRetryTextColor: Int,//空布局的重试文本颜色
    var defaultEmptyRetryVisible: Boolean,//空布局中是否隐藏重试按钮

    var defaultErrorText: String,//出错布局的提示文本
    var defaultErrorImg: Int,//出错布局的默认图片
    var defaultErrorRetryText: String,//出错布局的重试文本
    var defaultErrorRetryTextColor: Int,//出错布局的重试文本颜色
    var defaultErrorRetryVisible: Boolean,//出错布局中是否隐藏重试按钮

    var defaultLayoutBackgroundColor: Int //默认布局背景，包括加载中、空数据和出错布局
)
