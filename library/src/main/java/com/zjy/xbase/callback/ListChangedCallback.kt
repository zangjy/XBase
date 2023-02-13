package com.zjy.xbase.callback

import android.annotation.SuppressLint
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

/**
 * 文件名：ListChangedCallback
 * 创建者：ZangJiaYu
 * 创建日期：2023/2/13
 * 描述：
 */
object ListChangedCallback {

    /**
     * 可在数据改变时自动更新RecyclerView适配器，使用ObservableArrayList<*>.addOnListChangedCallback()来设置
     * @param adapter 要绑定的适配器
     * @return ObservableList.OnListChangedCallback<ObservableList<*>>
     */
    @SuppressLint("NotifyDataSetChanged")
    fun getListChangedCallback(adapter: RecyclerView.Adapter<*>): ObservableList.OnListChangedCallback<ObservableList<*>> {
        return object : ObservableList.OnListChangedCallback<ObservableList<*>>() {
            override fun onChanged(sender: ObservableList<*>) {
                adapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableList<*>,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(
                sender: ObservableList<*>,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<*>,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                if (itemCount == 1) {
                    adapter.notifyItemMoved(fromPosition, toPosition)
                } else {
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onItemRangeRemoved(
                sender: ObservableList<*>,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount)
            }
        }
    }
}