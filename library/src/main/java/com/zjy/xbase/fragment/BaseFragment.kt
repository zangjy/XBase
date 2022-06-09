package com.zjy.xbase.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    val binding: VB by lazy {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initObserver()
        setListener()
    }

    /**
     * 推荐在该方法中初始化ViewModel
     */
    abstract fun initViewModel()

    /**
     * 推荐在该方法中初始化数据
     */
    abstract fun initData()

    /**
     * 推荐在该方法中初始化Observer
     */
    abstract fun initObserver()

    /**
     * 推荐在该方法中设置点击事件
     */
    abstract fun setListener()


    /**
     * 获取ViewModel
     * @param viewModelStoreOwner 作用域
     * @param modelClass ViewModel类
     */
    fun <T : ViewModel> getViewModel(
        viewModelStoreOwner: ViewModelStoreOwner,
        modelClass: Class<T>
    ): T {
        return ViewModelProvider(viewModelStoreOwner)[modelClass]
    }
}