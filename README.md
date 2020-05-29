
## 一、背景

帮助 Android 开发者，快速上手。

## 二、开发者们

 欢迎共建
 
 1、[HailouWang](https://github.com/HailouWang)  微信 ：15911136758
 
## 三、问题

### 3.1、``git clone`` 之后，需要更改 ``./flutter_demo/.android/local.properties`` 中 环境变量信息

```properties
sdk.dir=/Users/hailuwang/Library/Android/sdk
flutter.sdk=/Users/hailuwang/tools/flutter
```

``flutter_demo`` 工程，如果遇到问题，需要先行编译，编译命令：

```groovy
flutter --no-color packages get
```

### 3.2、已适配androidx，Flutter 环境，需要更新到最新，建议更换Flutter 的更新源，配置如下：

``{flutterRoot}/flutter/packages/flutter_tools/gradle/flutter.gradle``

```properties
 buildscript {
     repositories {
-        google()
-        jcenter()
+maven { url 'https://maven.aliyun.com/repository/google' }
+maven { url 'https://maven.aliyun.com/repository/jcenter' }
+maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
     }
     dependencies {
         classpath 'com.android.tools.build:gradle:3.5.0'
@@ -41,7 +42,8 @@ android {
 apply plugin: FlutterPlugin
 
 class FlutterPlugin implements Plugin<Project> {
-    private static final String MAVEN_REPO      = "https://storage.googleapis.com/download.flutter.io";
+    //private static final String MAVEN_REPO      = "https://storage.googleapis.com/download.flutter.io";
+    private static final String MAVEN_REPO      = "https://storage.flutter-io.cn/download.flutter.io";
 
     // The platforms that can be passed to the `--Ptarget-platform` flag.
     private static final String PLATFORM_ARM32  = "android-arm";
```
 
## 四、UI

![页面1](https://upload-images.jianshu.io/upload_images/3828779-541d87094fa2eabc.gif?imageMogr2/auto-orient/strip|imageView2/2/w/344/format/webp)
![页面2](https://upload-images.jianshu.io/upload_images/3828779-6e5d9d3e61d4f9ce.gif?imageMogr2/auto-orient/strip|imageView2/2/w/344/format/webp)
![页面3](https://upload-images.jianshu.io/upload_images/3828779-eb0cec16069aee43.gif?imageMogr2/auto-orient/strip|imageView2/2/w/344/format/webp)

## 五、开源协议

Copyright (c) [2020] [wanghailu]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.







































