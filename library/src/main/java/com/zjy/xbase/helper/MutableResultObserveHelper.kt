package com.zjy.xbase.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kunminx.architecture.domain.message.MutableResult

/**
 * 文件名：MutableResultObserveHelper
 * 创建者：ZangJiaYu
 * 创建日期：2024/1/25
 * 描述：
 */
class MutableResultObserveHelper<T>(
    private val lifecycle: Lifecycle,
    private val mutableResult: MutableResult<T>,
    private val observer: Observer<in T>,
    private val observerType: ObserveType,
) : LifecycleEventObserver {

    init {
        lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                if (observerType == ObserveType.TYPE_FOREVER) {
                    mutableResult.observeForever(observer)
                } else if (observerType == ObserveType.TYPE_STICKY_FOREVER) {
                    mutableResult.observeStickyForever(observer)
                }
            }

            Lifecycle.Event.ON_DESTROY -> {
                mutableResult.removeObserver(observer)
                lifecycle.removeObserver(this)
            }

            else -> {}
        }
    }

    enum class ObserveType {
        TYPE_FOREVER,
        TYPE_STICKY_FOREVER
    }
}