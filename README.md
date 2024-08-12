# XBase

【自用】简单封装了网络请求和Base基类

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
//网络请求(https://github.com/square/retrofit)
api 'com.squareup.retrofit2:retrofit:2.11.0'
api 'com.squareup.retrofit2:converter-gson:2.11.0'
//Json解析(https://github.com/google/gson)
api 'com.google.code.gson:gson:2.11.0'
//Gson解析容错(https://github.com/getActivity/GsonFactory)
api 'com.github.getActivity:GsonFactory:9.6'
//非粘性的LiveData(https://github.com/KunMinX/UnPeek-LiveData)
api 'com.kunminx.arch:unpeek-livedata:7.8.0'

//ViewModel
api "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4"
//LiveData
api "androidx.lifecycle:lifecycle-livedata-ktx:2.8.4"
//Lifecycles only (without ViewModel or LiveData)
api "androidx.lifecycle:lifecycle-runtime-ktx:2.8.4"
//Saved state module for ViewModel
api "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.4"
api "androidx.navigation:navigation-fragment-ktx:2.7.7"
api "androidx.navigation:navigation-ui-ktx:2.7.7"
```

# XBase里已经添加过的权限

```
<uses-permission android:name="android.permission.INTERNET" />
```