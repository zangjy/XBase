package com.zjy.xbase.utils

import android.content.Context
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.PopupInfo
import com.lxj.xpopup.impl.LoadingPopupView
import com.lxj.xpopup.interfaces.SimpleCallback

/**
 * 文件名：DialogUtil
 * 创建者：ZangJiaYu
 * 创建日期：2022/4/15 0015
 * 描述：对话框工具类
 */
class DialogUtils(
    private val context: Context,
) {
    /**
     * 对话框的配置
     */
    var popupInfo = PopupInfo()

    /**
     * 使用该对象会在对话框关闭之后继续显示下一个对话框
     */
    val builder: XPopup.Builder by lazy {
        XPopup.Builder(context)
            .setPopupCallback(object : SimpleCallback() {
                override fun onDismiss(popupView: BasePopupView?) {
                    super.onDismiss(popupView)
                    //移除顶部的弹窗
                    removeTop()
                    //继续展示对话框
                    show()
                }
            })
    }

    /**
     * 等待对话框
     */
    private val loadingDialog: LoadingPopupView by lazy {
        XPopup.Builder(context).apply {
            dismissOnTouchOutside(false)
            dismissOnBackPressed(false)
        }.asLoading()
    }

    /**
     * 对话框队列
     */
    private val dialogQueue: MutableList<BasePopupView> = mutableListOf()

    /**
     * 修改对话框的配置(注：之前创建的不会应用该配置)
     * @param popupInfo 是否可关闭、暗色模式等
     */
    fun setInfo(popupInfo: PopupInfo) {
        this.popupInfo = popupInfo
        updatePopupInfo()
    }

    /**
     * 使对话框的配置生效
     */
    private fun updatePopupInfo() {
        builder.apply {
            dismissOnBackPressed(popupInfo.isDismissOnBackPressed)
            dismissOnTouchOutside(popupInfo.isDismissOnTouchOutside)
            autoDismiss(popupInfo.autoDismiss)
            hasShadowBg(popupInfo.hasShadowBg)
            hasBlurBg(popupInfo.hasBlurBg)
            atView(popupInfo.atView)
            popupAnimation(popupInfo.popupAnimation)
            customAnimator(popupInfo.customAnimator)
            popupHeight(popupInfo.popupHeight)
            popupWidth(popupInfo.popupWidth)
            maxWidth(popupInfo.maxWidth)
            maxHeight(popupInfo.maxHeight)
            autoOpenSoftInput(popupInfo.autoOpenSoftInput)
            moveUpToKeyboard(popupInfo.isMoveUpToKeyboard)
            popupPosition(popupInfo.popupPosition)
            hasStatusBarShadow(popupInfo.hasStatusBarShadow)
            hasStatusBar(popupInfo.hasStatusBar)
            hasNavigationBar(popupInfo.hasNavigationBar)
            navigationBarColor(popupInfo.navigationBarColor)
            isLightNavigationBar(popupInfo.isLightNavigationBar > 0)
            isLightStatusBar(popupInfo.isLightStatusBar > 0)
            statusBarBgColor(popupInfo.statusBarBgColor)
            offsetX(popupInfo.offsetX)
            offsetY(popupInfo.offsetY)
            enableDrag(popupInfo.enableDrag)
            isCenterHorizontal(popupInfo.isCenterHorizontal)
            isRequestFocus(popupInfo.isRequestFocus)
            autoFocusEditText(popupInfo.autoFocusEditText)
            isDarkTheme(popupInfo.isDarkTheme)
            isClickThrough(popupInfo.isClickThrough)
            isTouchThrough(popupInfo.isTouchThrough)
            enableShowWhenAppBackground(popupInfo.enableShowWhenAppBackground)
            isThreeDrag(popupInfo.isThreeDrag)
            isDestroyOnDismiss(popupInfo.isDestroyOnDismiss)
            borderRadius(popupInfo.borderRadius)
            positionByWindowCenter(popupInfo.positionByWindowCenter)
            isViewMode(popupInfo.isViewMode)
            shadowBgColor(popupInfo.shadowBgColor)
            animationDuration(popupInfo.animationDuration)
            keepScreenOn(popupInfo.keepScreenOn)
        }
    }

    /**
     * 展示等待对话框
     */
    fun showLoadingDialog() {
        loadingDialog.show()
    }

    /**
     * 展示等待对话框
     * @param title 标题，如果要修改等待对话框标题的话，直接调用本方法就可以了
     */
    fun showLoadingDialog(title: String) {
        if (!loadingDialog.isShow) {
            loadingDialog.show()
        }
        loadingDialog.setTitle(title)
    }

    /**
     * 关闭等待对话框
     */
    fun hideLoadingDialog() {
        if (loadingDialog.isShow) {
            loadingDialog.dismiss()
        }
    }

    /**
     * 展示弹窗(队列方式)
     * @param popupView BasePopupView
     */
    fun showPopup(popupView: BasePopupView) {
        dialogQueue.add(popupView)
        show()
    }

    /**
     * 清空所有弹窗
     */
    fun removeAll() {
        dialogQueue.forEach {
            it.dismiss()
        }
        dialogQueue.clear()
    }

    /**
     * 展示队列中的第一个对话框(如果有并且对话框处于未展示状态时)
     */
    private fun show() {
        if (dialogQueue.size > 0 && !dialogQueue[0].isShow) {
            dialogQueue[0].show()
        }
    }

    /**
     * 从队列中移除顶部的对话框(如果有的话)
     */
    private fun removeTop() {
        if (dialogQueue.size > 0) {
            //从队列中移除
            dialogQueue.removeAt(0)
        }
    }
}