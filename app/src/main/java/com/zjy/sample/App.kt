package com.zjy.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.zjy.xbase.utils.StatusLayoutUtils

/**
 * 文件名：App
 * 创建者：ZangJiaYu
 * 创建日期：2022/5/12 0012
 * 描述：
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        StatusLayoutUtils.init(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}