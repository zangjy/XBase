# XBase

【自用】新建项目时减少一点基础的封装操作

# 使用方法

[![](https://jitpack.io/v/zangjy/XBase.svg)](https://jitpack.io/#zangjy/XBase)

1.添加依赖

```
implementation 'com.github.zangjy:XBase:版本号👆'
```

2.在工程根目录的`build.gradle`中添加如下：

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

或者在`settings.gradle`中添加如下：

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //引用通过JitPack.io发布的第三方库
        maven { url 'https://www.jitpack.io' }
    }
}
```

3.在APP的`build.gradle`中添加如下：

```
//开启数据绑定
dataBinding {
    enabled = true
}
```

4.混淆

```
#XBase
-keep class com.zjy.xbase.** { *; }
-keepclassmembers class ** implements androidx.viewbinding.ViewBinding {
    public static ** bind(***);
    public static ** inflate(***);
}
```

# XBase里已经添加过的依赖

```
//网络请求：https://github.com/square/retrofit
api 'com.squareup.retrofit2:retrofit:2.9.0'
api 'com.squareup.retrofit2:converter-gson:2.9.0'
//Gson解析容错：https://github.com/getActivity/GsonFactory
api 'com.github.getActivity:GsonFactory:8.0'
//Json解析框架：https://github.com/google/gson
api 'com.google.code.gson:gson:2.10.1'
//解决LiveData出现数据倒灌问题：https://github.com/KunMinX/UnPeek-LiveData
api 'com.kunminx.arch:unpeek-livedata:7.8.0'

// ViewModel
api 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
// LiveData
api 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
// Lifecycles only (without ViewModel or LiveData)
api 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'
// Saved state module for ViewModel
api 'androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1'
api 'androidx.navigation:navigation-fragment-ktx:2.6.0'
api 'androidx.navigation:navigation-ui-ktx:2.6.0'
```

# XBase里已经添加过的权限

```
<uses-permission android:name="android.permission.INTERNET" />
```

# 常用框架

内存泄漏检测：<https://github.com/square/leakcanary>

PDF预览：<https://github.com/barteksc/AndroidPdfViewer>

XPopup弹窗库：<https://github.com/li-xiaojun/XPopup>

XPopupExt：<https://github.com/li-xiaojun/XPopupExt>

切换不同状态布局：<https://github.com/liangjingkanji/StateLayout>

ARouter：<https://github.com/alibaba/ARouter>

沉浸式状态栏：<https://github.com/gyf-dev/ImmersionBar>

工具类：<https://github.com/Blankj/AndroidUtilCode>

OKDownLoad：<https://github.com/lingochamp/okdownload>

GSYVideoPlayer：<https://github.com/CarGuo/GSYVideoPlayer>

AgentWeb：<https://github.com/Justson/AgentWeb>

图片选择器：<https://github.com/LuckSiege/PictureSelector>

Banner：<https://github.com/youth5201314/banner>

Glide：<https://github.com/bumptech/glide>

Lottie：<https://github.com/airbnb/lottie-android>

EventBus：<https://github.com/greenrobot/EventBus>

TitleBar：<https://github.com/getActivity/TitleBar>

吐司框架：<https://github.com/getActivity/ToastUtils>

Gson解析容错：<https://github.com/getActivity/GsonFactory>

权限请求框架：<https://github.com/guolindev/PermissionX>

智能下拉刷新：<https://github.com/scwang90/SmartRefreshLayout>

RecyclerView适配器：<https://github.com/CymChad/BaseRecyclerViewAdapterHelper>

异常捕获框架：<https://github.com/Ereza/CustomActivityOnCrash>

日期城市选择器：<https://github.com/Bigkoo/Android-PickerView>

扫码库：<https://github.com/yuzhiqiang1993/zxing>

FlexBoxLayout：<https://github.com/google/flexbox-layout>

M3U8下载器：<https://github.com/Jay-Goo/M3U8Downloader>

解决LiveData出现数据倒灌问题：<https://github.com/KunMinX/UnPeek-LiveData>