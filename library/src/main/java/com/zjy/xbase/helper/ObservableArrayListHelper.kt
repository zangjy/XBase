package com.zjy.xbase.helper

import android.annotation.SuppressLint
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * 文件名：ObservableArrayListHelper
 * 创建者：ZangJiaYu
 * 创建日期：2023/3/29
 * 描述：可在数据改变时自动更新RecyclerView适配器
 */
@SuppressLint("NotifyDataSetChanged")
class ObservableArrayListHelper<T>(
    private val list: ObservableArrayList<T>,
    private var adapter: RecyclerView.Adapter<*>,
    lifecycle: Lifecycle,
) : ObservableList.OnListChangedCallback<ObservableArrayList<T>>(), LifecycleEventObserver {

    init {
        list.addOnListChangedCallback(this)
        lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            list.removeOnListChangedCallback(this)
            source.lifecycle.removeObserver(this)
        }
    }

    override fun onChanged(sender: ObservableArrayList<T>?) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeChanged(
        sender: ObservableArrayList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        adapter.notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun onItemRangeInserted(
        sender: ObservableArrayList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        adapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeMoved(
        sender: ObservableArrayList<T>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int,
    ) {
        if (itemCount == 1) {
            adapter.notifyItemMoved(fromPosition, toPosition)
        } else {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemRangeRemoved(
        sender: ObservableArrayList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount)
    }
}