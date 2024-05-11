package com.zjy.sample.activity

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zjy.sample.databinding.ActivityMainBinding
import com.zjy.sample.fragment.Fragment1
import com.zjy.sample.fragment.Fragment2
import com.zjy.sample.viewmodel.MainVM
import com.zjy.xbase.activity.BaseActivity
import com.zjy.xbase.adapter.ViewPager2Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mVM: MainVM by viewModels()

    private var job: Job? = null

    private val fragments: MutableList<Fragment> by lazy {
        mutableListOf(Fragment1(), Fragment2())
    }

    override fun initObservers() {

    }

    override fun initListeners() {

    }

    override fun initData() {
        setAdapter()

        job = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(100L)
                mVM.desValueChangeEvent.postValue(generateRandomString())
            }
        }
    }

    private fun setAdapter() {
        with(binding.pager) {
            (getChildAt(0) as RecyclerView).overScrollMode = android.view.View.OVER_SCROLL_NEVER
            offscreenPageLimit = fragments.size
            adapter = ViewPager2Adapter(this@MainActivity, object : ViewPager2Adapter.GetData {
                override fun getItemCount(): Int {
                    return fragments.size
                }

                override fun createFragment(position: Int): Fragment {
                    return fragments[position]
                }
            })
        }
    }

    private fun generateRandomString(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..10).map { charset.random() }.joinToString("")
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}