package com.zjy.xbase.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> AppCompatActivity.inflateBindingWithGeneric(
    layoutInflater: LayoutInflater,
): VB = withGenericBindingClass(this) { clazz ->
    clazz.getMethod(
        "inflate",
        LayoutInflater::class.java
    ).invoke(null, layoutInflater) as VB
}.also { binding ->
    if (binding is ViewDataBinding) {
        binding.lifecycleOwner = this
    }
}

@Suppress("UNCHECKED_CAST")
@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Fragment.inflateBindingWithGeneric(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean,
): VB = withGenericBindingClass(this) { clazz ->
    clazz.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, layoutInflater, parent, attachToParent) as VB
}.also { binding ->
    if (binding is ViewDataBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
    }
}

@Suppress("UNCHECKED_CAST")
private fun <VB : ViewBinding> withGenericBindingClass(
    any: Any,
    block: (Class<VB>) -> VB,
): VB {
    val aClass = try {
        (any.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
    } catch (e: Exception) {
        throw e
    }
    return block.invoke(aClass)
}