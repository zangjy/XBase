package com.zjy.xbase.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 文件名：BaseBindingHolder
 * 创建者：ZangJiaYu
 * 创建日期：2023/5/20
 * 描述：
 */
open class BaseBindingHolder<VB : ViewBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root)