package com.zjy.xbase.utils

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.zjy.xbase.R
import com.zjy.xbase.bean.StatusLayoutManagerConfig
import com.zjy.xbase.library.layoutmgr.OnStatusChildClickListener
import com.zjy.xbase.library.layoutmgr.StatusLayoutManager

/**
 * 文件名：StatusLayoutUtil
 * 创建者：ZangJiaYu
 * 创建日期：2022/4/26
 * 描述：切换不同状态布局工具类
 */

class StatusLayoutUtils : OnStatusChildClickListener {

    //点击事件监听
    private var listener: OnStatusChildClickListener? = null

    companion object {
        //Application上下文
        private var application: Application? = null

        private val layoutInflate by lazy {
            LayoutInflater.from(application!!)
        }

        //加载中的布局ID
        private var loadingLayoutId: Int = R.layout.layout_status_layout_manager_loading

        //加载中的布局
        val loadingView: View by lazy {
            layoutInflate.inflate(loadingLayoutId, null)
        }

        //数据为空的布局ID
        private var emptyLayoutId: Int = R.layout.layout_status_layout_manager_empty

        //数据为空的布局
        val emptyView: View by lazy {
            layoutInflate.inflate(emptyLayoutId, null)
        }

        //出错的布局ID
        private var errorLayoutId: Int = R.layout.layout_status_layout_manager_error

        //出错的布局
        val errorView: View by lazy {
            layoutInflate.inflate(errorLayoutId, null)
        }

        //空布局重试的按钮的ID
        private var emptyRetryId: Int = R.id.status_layout_manager_bt_status_empty_click

        //出错布局重试的按钮的ID
        private var errorRetryId: Int = R.id.status_layout_manager_bt_status_error_click

        //切换布局的配置文件
        private lateinit var config: StatusLayoutManagerConfig

        /**
         * 如果要使用该工具类，必须在Application中调用本方法进行初始化(使用该方法将使用默认的布局)
         * StatusLayoutManager每次使用都会将布局转换为View对象，封装之后只需要在初始化的时候转换一次即可，但必须是要求在Application中进行初始化，否则会引起内存泄漏，或者你也可以不使用该工具类，而直接使用框架
         */
        fun init(application: Application) {
            this.application = application
            initConfig()
        }

        /**
         * 如果要使用该工具类，必须在Application中调用本方法进行初始化
         * StatusLayoutManager每次使用都会将布局转换为View对象，封装之后只需要在初始化的时候转换一次即可，但必须是要求在Application中进行初始化，否则会引起内存泄漏，或者你也可以不使用该工具类，而直接使用框架
         * @param application 传入Application
         * @param loadingLayoutId 加载中布局(可以将默认布局 R.layout.layout_status_layout_manager_loading 传进来)
         * @param emptyLayoutId 空布局(可以将默认布局 R.layout.layout_status_layout_manager_empty 传进来)
         * @param errorLayoutId 出错的布局(可以将默认布局 R.layout.layout_status_layout_manager_error 传进来)
         * @param emptyRetryId 空布局重试的按钮的ID(可以将默认的 R.id.bt_status_empty_click 传进来)
         * @param errorRetryId 出错布局重试的按钮的ID(可以将默认的 R.id.bt_status_error_click 传进来)
         */
        fun init(
            application: Application,
            loadingLayoutId: Int,
            emptyLayoutId: Int,
            errorLayoutId: Int,
            emptyRetryId: Int,
            errorRetryId: Int
        ) {
            this.application = application
            this.loadingLayoutId = loadingLayoutId
            this.emptyLayoutId = emptyLayoutId
            this.errorLayoutId = errorLayoutId
            this.emptyRetryId = emptyRetryId
            this.errorRetryId = errorRetryId
            initConfig()
        }

        /**
         * 初始化配置
         */
        private fun initConfig() {
            config = StatusLayoutManagerConfig(
                defaultLoadingText = application!!.getString(R.string.status_layout_manager_loading),

                defaultEmptyText = application!!.getString(R.string.status_layout_manager_empty),
                defaultEmptyImg = R.drawable.status_layout_manager_ic_empty,
                defaultEmptyRetryText = application!!.getString(R.string.status_layout_manager_retry),
                defaultEmptyRetryTextColor = ContextCompat.getColor(
                    application!!,
                    R.color.status_layout_manager_click_view_text_color
                ),
                defaultEmptyRetryVisible = true,

                defaultErrorText = application!!.getString(R.string.status_layout_manager_error),
                defaultErrorImg = R.drawable.status_layout_manager_ic_error,
                defaultErrorRetryText = application!!.getString(R.string.status_layout_manager_retry),
                defaultErrorRetryTextColor = ContextCompat.getColor(
                    application!!,
                    R.color.status_layout_manager_click_view_text_color
                ),
                defaultErrorRetryVisible = true,

                defaultLayoutBackgroundColor = ContextCompat.getColor(
                    application!!,
                    R.color.status_layout_manager_background_color
                )
            )
        }

        /**
         * 设置默认展示的的一些参数
         * @param config 配置信息
         */
        fun setConfig(config: StatusLayoutManagerConfig) {
            this.config = config
        }

        /**
         * 获取当前的配置
         * @return config 配置信息
         */
        fun getConfig(): StatusLayoutManagerConfig {
            return this.config
        }
    }

    /**
     * 维护添加的所有View
     */
    private val viewMap: MutableMap<String, StatusLayoutManager> by lazy {
        mutableMapOf()
    }

    /**
     * 增加一个需要切换布局的View
     * @param view 需要切换的View
     * @param stringTag 如果页面中设置了多个，可以根据该字段进行区分
     * @return StatusLayoutManager 返回当前View对应的实例
     */
    fun add(view: View, stringTag: String) {
        view.tag = stringTag
        viewMap[stringTag] = StatusLayoutManager.Builder(view)
            .setLoadingLayout(loadingView)
            .setErrorLayout(errorView)
            .setEmptyLayout(emptyView)
            .setErrorClickViewID(errorRetryId)
            .setEmptyClickViewID(emptyRetryId)
            .setDefaultLoadingText(config.defaultLoadingText)
            .setDefaultEmptyText(config.defaultEmptyText)
            .setDefaultEmptyImg(config.defaultEmptyImg)
            .setDefaultEmptyClickViewText(config.defaultEmptyRetryText)
            .setDefaultEmptyClickViewTextColor(config.defaultEmptyRetryTextColor)
            .setDefaultEmptyClickViewVisible(config.defaultEmptyRetryVisible)
            .setDefaultErrorText(config.defaultErrorText)
            .setDefaultEmptyImg(config.defaultErrorImg)
            .setDefaultErrorClickViewText(config.defaultErrorRetryText)
            .setDefaultErrorClickViewTextColor(config.defaultErrorRetryTextColor)
            .setDefaultErrorClickViewVisible(config.defaultErrorRetryVisible)
            .setDefaultLayoutsBackgroundColor(config.defaultLayoutBackgroundColor)
            .setOnStatusChildClickListener(this)
            .build()
    }

    /**
     * 展示加载中布局
     * @param stringTag 传入add时候设置的Tag
     */
    fun showLoadingLayout(stringTag: String) {
        viewMap[stringTag]?.showLoadingLayout()
    }

    /**
     * 展示空布局
     * @param stringTag 传入add时候设置的Tag
     */
    fun showEmptyLayout(stringTag: String) {
        viewMap[stringTag]?.showEmptyLayout()
    }

    /**
     * 展示错误布局
     * @param stringTag 传入add时候设置的Tag
     */
    fun showErrorLayout(stringTag: String) {
        viewMap[stringTag]?.showErrorLayout()
    }

    /**
     * 展示成功布局
     * @param stringTag 传入add时候设置的Tag
     */
    fun showSuccessLayout(stringTag: String) {
        viewMap[stringTag]?.showSuccessLayout()
    }

    /**
     * 展示自定义布局
     * @param layoutId 布局的ID
     * @param stringTag 传入add时候设置的Tag
     */
    fun showCustomLayout(layoutId: Int, stringTag: String) {
        viewMap[stringTag]?.showCustomLayout(
            layoutInflate.inflate(layoutId, null).apply { tag = stringTag })
    }

    /**
     * 展示自定义布局
     * @param layoutId 布局的ID
     * @param ids 按钮的ID，可以传入多个
     * @param stringTag 传入add时候设置的Tag
     */
    fun showCustomLayout(layoutId: Int, vararg ids: Int, stringTag: String) {
        viewMap[stringTag]?.showCustomLayout(
            layoutInflate.inflate(layoutId, null).apply { tag = stringTag }, *ids
        )
    }

    /**
     * 设置点击事件
     * @param listener 推荐在Activity中实现该接口，统一去处理
     */
    fun setOnClickListener(listener: OnStatusChildClickListener) {
        this.listener = listener
    }

    override fun onEmptyChildClick(view: View, stringTag: String) {
        listener?.onEmptyChildClick(view, stringTag)
    }

    override fun onErrorChildClick(view: View, stringTag: String) {
        listener?.onErrorChildClick(view, stringTag)
    }

    override fun onCustomerChildClick(view: View, stringTag: String) {
        listener?.onCustomerChildClick(view, stringTag)
    }
}