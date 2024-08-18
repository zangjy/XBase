package com.zjy.sample.ui.intent

import com.zjy.xbase.ui.intent.IUiIntent

/**
 * 文件名：MainIntent
 * 创建者：ZangJiaYu
 * 创建日期：2024/8/18
 * 描述：
 */
sealed class MainIntent : IUiIntent {
    object LoginByRequest : MainIntent()
    object LoginByDoAsync : MainIntent()
}