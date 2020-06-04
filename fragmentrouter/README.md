
## 一、FragmentRouter 要解决的问题

当Android 采用 单 Activity、多 Fragment 模式时，业务逻辑下沉到 Fragment中，Activity 的作用，一定程度上，就是个“壳”。

此时，不可避免的，会定义一堆的 “壳” Activity ，这也是第一个要解决的问题。

Fragment 创建出来，是要通过 FragmentManager 添加到 “壳” Activity 中，此部分代码逻辑，几乎没什么差异，但却要 ``CV`` 操作很多遍，这是要解决的第二个问题。  

## 二、使用方式

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    ...
    implementation 'com.github.hailouwang:fragmentrouter:1.0.0'
}
```

## 三、原理

1、通过 ``activity-alias`` 别名的形式，来避免创建 一堆 “壳” Activity。

```
<activity-alias
    android:name=".fragment1.FragmentDemosActivity1"
    android:label="FragmentDemosActivity1"
    android:targetActivity=".singleActivity.RouterActivity">
```

2、将Fragment的管理逻辑，封装在单独的类中，对外提供注入 ``Fragment`` 的接口。

例如：在AndroidManifest中，通过meta-data 来注入 Fragment 信息。

```
<meta-data
    android:name="BMRouterFragmentActivity.FRAGMENT_CLASS"
    android:value="com.hailouwang.fragmentrouter.sample.fragment3.FragmentDemos3" />
```

3、权限

注入的Fragment，拥有和Activity 相同的权限，故：要放置外部的Fragment 注入到App内，故：要对 Fragment 做合法性校验。

```
override fun isValidFragment(fragmentName: String?): Boolean {
    return ENTRY_FRAGMENTS.contains(fragmentName)
}
```

## 四、备注

方案实现来自：Android 系统设置 App