package com.zjy.xbase.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    val binding get() = requireNotNull(_binding) { "ViewBinding已被销毁" }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            val type = javaClass.genericSuperclass as ParameterizedType
            val aClass = type.actualTypeArguments[0] as Class<*>
            val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            _binding = method.invoke(null, layoutInflater) as VB
            //销毁的时候释放ViewBinding
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    handler.post { _binding = null }
                }
            })
        }
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initObservers()
        initListeners()
    }

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun initData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun initObservers()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun initListeners()
}