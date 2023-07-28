package com.zjy.xbase.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * 文件名：ViewPager2Adapter
 * 创建者：ZangJiaYu
 * 创建日期：2023/7/25
 * 描述：
 */
class ViewPager2Adapter : FragmentStateAdapter {
    private var listener: GetData

    constructor(fragment: Fragment, listener: GetData) : super(fragment) {
        this.listener = listener
    }

    constructor(fragmentActivity: FragmentActivity, listener: GetData) : super(fragmentActivity) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return listener.getItemCount()
    }

    override fun createFragment(position: Int): Fragment {
        return listener.createFragment(position)
    }

    interface GetData {
        fun getItemCount(): Int
        fun createFragment(position: Int): Fragment
    }
}