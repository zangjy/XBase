package com.zjy.xbase.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 在Fragment中获取ViewModel实例
 * @receiver Fragment
 * @param viewModelStoreOwner 作用域
 * @param modelClass ViewModel类
 * @return ViewModel实例
 */
fun <T : ViewModel> Fragment.getViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    modelClass: Class<T>
): T {
    return ViewModelProvider(viewModelStoreOwner)[modelClass]
}