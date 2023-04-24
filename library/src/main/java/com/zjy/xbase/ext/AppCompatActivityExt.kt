package com.zjy.xbase.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 在Activity中获取ViewModel实例
 * @receiver AppCompatActivity
 * @param viewModelStoreOwner 作用域
 * @param modelClass ViewModel类
 * @return ViewModel实例
 */
fun <T : ViewModel> AppCompatActivity.getViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    modelClass: Class<T>
): T {
    return ViewModelProvider(viewModelStoreOwner)[modelClass]
}