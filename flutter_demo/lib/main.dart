import 'dart:async';
import 'dart:io';

import 'package:fluro/fluro.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutterdemo/app/Application.dart';
import 'package:flutterdemo/provider/common/AppSettingsModel.dart';
import 'package:flutterdemo/provider/common/UserInfoModel.dart';
import 'package:flutterdemo/router/routes.dart';
import 'package:flutterdemo/widget/collapsebox/CollapseBoxWidgetPageDemo.dart';
import 'package:oktoast/oktoast.dart';
import 'package:provider/provider.dart';

import 'media/MediaQueryDataWidget.dart';
import 'plugin/PluginBatteryWidget.dart';
import 'provider/ProviderDemo.dart';
import 'simple_page_widgets.dart';
import 'ui/demos/DemoList.dart';
import 'ui/home.dart';
import 'widget/NativeViewDemo.dart';
import 'widget/flex/FlexWidget.dart';
import 'widget/lifecycle/StateLifeCycle.dart';
import 'widget/oktoast/OKToastWidget.dart';
import 'widget/sliver/CustomScrollViewDemo.dart';
import 'widget/sliver/SliverAppBarDemo.dart';
import 'widget/sliver/SliverPersistentHeaderShrinkOffsetDemo.dart';
import 'widget/sliver/SliverPersistentHeaderStickyDemo.dart';
import 'widget/willpopscope/WillPopScopeWidget.dart';

/// => 单行函数或者方法的简写
/// void main() => runApp(MyApp());
void main() {
  WidgetsFlutterBinding.ensureInitialized();

  //注册Flutter框架的异常回调
  FlutterError.onError = (FlutterErrorDetails details) async {
    if (kDebugMode) {
      FlutterError.dumpErrorToConsole(details);
    }
    print('FlutterError: ${details}');
    Zone.current.handleUncaughtError(details.exception, details.stack);
  };

  //使用runZone方法将runApp的运行放置在Zone中，并提供统一的异常回调
  runZonedGuarded<Future<void>>(() async {
    runApp(MyApp());
  }, (Object error, StackTrace stackTrace) {
    print('_runZonedGuarded ReportError: ${error}');
    print('_runZonedGuarded ReportStackTrace: ${stackTrace}');

    if (kDebugMode) {
      _reportError(error, stackTrace);
    } else {
      // Whenever an error occurs, call the `_reportError` function. This sends
      // Dart errors to the dev console or Sentry depending on the environment.
      _reportError(error, stackTrace);
    }
  });
}

//上报数据至Bugly、CrashEye
Future<Null> _reportError(dynamic error, dynamic stackTrace) async {
  /// 上报 bug 到三方稳定性监控平台
}

// TopLevel
// 放置全局状态 数据，任何组件都依赖的部分
class MyApp extends StatelessWidget {
  MyApp() {
    /// 初始化页面
    _initMyApp();

    /// 官方路由
    _initFlutterRouter();

    /// FlutterBoost 路由
    _initFlutterBoost();
  }

  @override
  Widget build(BuildContext context) {
    /// 全局数据共享
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_context) => AppSettingsModel()),
          ChangeNotifierProvider(
            create: (_context) {
              print("hlwang MyApp UserInfoModel ");
              return new UserInfoModel();
            },
          )
        ],
        child: OKToast(
          child: MediaQuery(
            data: MediaQueryData.fromWindow(WidgetsBinding.instance.window),
            child: HomeUI(),
          ),
        ));
  }

  void _initMyApp() {
    /// 设置dark样式
    if (Platform.isAndroid) {
      // Android 设备，设置 系统主题配置
      SystemUiOverlayStyle style = SystemUiOverlayStyle.dark;
      SystemChrome.setSystemUIOverlayStyle(style);
    }
  }

  void _initFlutterRouter() {
    final router = Router();
    Routers.configureRoutes(router);
    Application.router = router;
  }

  /// 全局 Flutter 路由、Events、Channels
  void _initFlutterBoost() {
    /// Routers
    _initFlutterBoostRouter();

    /// Events
    /// Channels
  }

  void _initFlutterBoostRouter() {
    FlutterBoost.singleton.registerPageBuilders(<String, PageBuilder>{
      'embeded': (String pageName, Map<String, dynamic> params, String _) =>
          EmbeddedFirstRouteWidget(),
      'first': (String pageName, Map<String, dynamic> params, String _) =>
          FirstRouteWidget(),
      'firstFirst': (String pageName, Map<String, dynamic> params, String _) =>
          FirstFirstRouteWidget(),
      'second': (String pageName, Map<String, dynamic> params, String _) =>
          SecondRouteWidget(),
      'secondStateful':
          (String pageName, Map<String, dynamic> params, String _) =>
              SecondStatefulRouteWidget(),
      'tab': (String pageName, Map<String, dynamic> params, String _) =>
          TabRouteWidget(),
      'platformView':
          (String pageName, Map<String, dynamic> params, String _) =>
              PlatformRouteWidget(),
      'flutterFragment':
          (String pageName, Map<String, dynamic> params, String _) =>
              FragmentRouteWidget(params),

      ///可以在native层通过 getContainerParams 来传递参数
      'flutterPage': (String pageName, Map<String, dynamic> params, String _) {
        print('flutterPage params:$params');

        return FlutterRouteWidget(params: params);
      },
      'main': (String pageName, Map<String, dynamic> params, String _) =>
          new TabContainer(),
      '/a': (String pageName, Map<String, dynamic> params, String _) =>
          new MyPage14(title: 'Page A'),
      '/b': (String pageName, Map<String, dynamic> params, String _) =>
          new MyPage14(title: 'Page B'),
      '/c': (String pageName, Map<String, dynamic> params, String _) =>
          new MyPage14(title: 'Page C'),
      '/d': (String pageName, Map<String, dynamic> params, String _) =>
          new CustomButtonWidget(),
      '/e': (String pageName, Map<String, dynamic> params, String _) =>
          new IntentsFromOuterClassPage(),
      '/provider': (String pageName, Map<String, dynamic> params, String _) =>
          new ProviderDemo(),
      '/battery_plugin':
          (String pageName, Map<String, dynamic> params, String _) =>
              new PluginBatteryWidget(),
      "01 Hello World!!!":
          (String pageName, Map<String, dynamic> params, String _) => Center(
              child: new Text("Hello,World", textDirection: TextDirection.ltr)),
      "02 布局": (String pageName, Map<String, dynamic> params, String _) =>
          new MaterialApp(
              title: '布局', // used by the OS task switcher
              home: new MyScaffold()),
      "03 使用 Material 组件":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: 'Flutter Tutorial',
                home: new TutorialHome(),
              ),
      "04 手势": (String pageName, Map<String, dynamic> params, String _) =>
          new MaterialApp(
            title: '手势',
            home: new MyButton(),
          ),
      "05 根据用户输入改变widget":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: 'StateFullWidget',
                home: new Counter(),
              ),
      "06 数据搜集 & 数据展示 & 状态保存分离":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: 'StateFullWidget',
                home: new Counter06(),
              ),
      "07 综合案例：购物车": (String pageName, Map<String, dynamic> params, String _) =>
          new MaterialApp(
            title: 'StateFullWidget',
            home: new ShoppingList(
              products: <Product>[
                new Product(name: 'Eggs'),
                new Product(name: 'Flour'),
                new Product(name: 'Chocolate chips'),
              ],
            ),
          ),
      "08 与 Android 相比：StatelessWidget":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: 'StateFullWidget',
                home: new Text(
                  '08 与 Android 相比：StatelessWidget',
                  style: new TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
      "09 与 Android 相比：StatefullWidget":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: 'StateFullWidget',
                home: new SampleAppPage(),
              ),
      "10 与 Android 相比：添加或者删除组件":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '10 添加/删除组件',
                home: new SampleAppPage10(),
              ),
      "11 与 Android 相比：View.animate":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '11 与 Android 相比：View.animate',
                home: new SampleAppPage11(),
              ),
      "12 使用Canvas draw/paint":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '12 与 Android 相比：View.animate',
                home: new Signature(),
              ),
      "13 自定义Widgets":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '13 自定义Widgets',
                home: new CustomButtonWidget(),
              ),
      "14 Intents --> Navigator切换到命名的路由":
          (String pageName, Map<String, dynamic> params, String _) {
//            if (count14 % 4 == 0) {
//              Navigator.of(context).pushNamed('/a', arguments: "hi");
//            } else if (count14 % 4 == 1) {
//              Navigator.of(context).pushNamed('/b');
//            } else if (count14 % 4 == 2) {
//              Navigator.of(context).pushNamed('/c');
//            } else if (count14 % 4 == 3) {
//              Navigator.of(context).pushNamed('/d');
//            }
      },

      "15 Intents --> 处理来自外部应用程序传入的Intents":
          (String pageName, Map<String, dynamic> params, String _) =>
              new IntentsFromOuterClassPage(),
      "16 异步请求 runOnUiThread":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '16 ---> 异步请求 runOnUiThread',
                home: new ListAsyncPage(),
              ),
      "17 Isolate 异步请求 runOnUiThread":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '17 ---> Isolate 异步请求 runOnUiThread',
                home: new ListAsyncPageIsolate(),
              ),
      "18 在Flutter中显示进度指示器":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '18 在Flutter中显示进度指示器',
                home: new ProgressIndicatorDemo(),
              ),
      "19 在Flutter中监听生命周期":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                title: '19 在Flutter中监听生命周期',
                home: new LifecycleWatcher(),
              ),
      "20 Google Provider Demo":
          (String pageName, Map<String, dynamic> params, String _) {
        // Navigator.of(context).pushNamed('/provider');
      },
      "21 State 生命周期":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '21 State 生命周期', // used by the OS task switcher
                  home: new StateLifeCycle()),
      "22 MediaQueryData 屏幕信息": (String pageName, Map<String, dynamic> params,
              String _) =>
          new MaterialApp(
              title: '22 MediaQueryDataWidget ', // used by the OS task switcher
              home: new MediaQueryDataWidget()),
      "23 OKToast": (String pageName, Map<String, dynamic> params, String _) =>
          new MaterialApp(
              title: '23 OKToast ', // used by the OS task switcher
              home: new OKToastWidget()),
      "24 WillPopScope 防误触":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '24 WillPopScope 防误触 ', // used by the OS task switcher
                  home: new WillPopScopeWidget()),
      "25 Flex 布局": (String pageName, Map<String, dynamic> params, String _) =>
          new MaterialApp(
              title: '25 Flex ', // used by the OS task switcher
              home: new FlexWidget()),
      "26 Flutter Plugin 电量 Battery！！！":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '26 Flutter Plugin 电量 Battery！！！',
                  // used by the OS task switcher
                  home: new PluginBatteryWidget()),
      "27 Sliver Grid Sliver List": (String pageName,
              Map<String, dynamic> params, String _) =>
          new MaterialApp(
              title: '27 Sliver Grid Sliver List',
              // used by the OS task switcher
              home: CustomScrollViewDemo(title: '27 Sliver Grid Sliver List')),
      "28 Sliver AppBar":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '28 Sliver AppBar',
                  // used by the OS task switcher
                  home: SliverAppBarDemo(title: '28 Sliver AppBa')),
      "29 Sliver 吸顶效果": (String pageName, Map<String, dynamic> params,
              String _) =>
          new MaterialApp(
              title: '29 Sliver 吸顶效果',
              // used by the OS task switcher
              home: SliverPersistentHeaderStickyDemo(title: '29 Sliver 吸顶效果')),
      "30 Sliver 过度效果":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '30 Sliver 顶部过度效果',
                  // used by the OS task switcher
                  home: SliverPersistentHeaderShrinkOffsetDemo(
                      title: '30 Sliver 过度效果')),
      "31 借助原生视图能力，android 绘制":
          (String pageName, Map<String, dynamic> params, String _) =>
              new MaterialApp(
                  title: '31 借助原生视图能力，android 绘制',
                  // used by the OS task switcher
                  home: NativeViewDemo()),
      "32 CollapseBoxWidgetPageDemo 案例": (String pageName, Map<String, dynamic> params,
              String _) =>
          new MaterialApp(
              title: pageName,
              // used by the OS task switcher
              home:
              CollapseBoxWidgetPageDemo(title: "32 CollapseBoxWidgetPageDemo 案例")),
    });
  }
}

class AppStateWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _AppState();
}

class _AppState extends State<AppStateWidget> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Boost example',
        theme: ThemeData(
          /// 主题主色，决定导航栏颜色
          primaryColor: Colors.white,

          /// 背景颜色
          scaffoldBackgroundColor: Colors.white,

          /// 分割线颜色
          dividerColor: Color.fromRGBO(247, 247, 247, 1),

          /// 字体主题，包括标题、body等文字样式
          textTheme: TextTheme(
            headline1: TextStyle(
              color: Color.fromRGBO(42, 43, 45, 1),
              fontSize: 16.0,
              fontWeight: FontWeight.normal,
            ),
          ),
        ),

        /// 本地化配置
        localizationsDelegates: [
          // ... app-specific localization delegate[s] here
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
        ],

        /// 支持的语言
        supportedLocales: [
          const Locale('zh', 'CN'),
          const Locale('en', 'US')
          // ... other locales the app supports
        ],
        builder: (BuildContext c, Widget w) {
          return MediaQuery(

              /// 注意：此处非常关键，设置文字大小不随系统设置改变
              data: MediaQuery.of(c).copyWith(textScaleFactor: 1.0),
              child: FlutterBoost.init(postPush: _onRoutePushed)(c, w));
        },

        /// 默认页面背景
        home: Container(color: Colors.white));
  }

  void _onRoutePushed(
    String pageName,
    String uniqueId,
    Map<String, dynamic> params,
    Route<dynamic> route,
    Future<dynamic> _,
  ) {
    // Now，Do Nothing！！！
    // 类似 Android 中的 registerActivityLifecycleCallbacks
    print("FlutterBoost onRoutePushed packageName:${pageName}!");
  }
}
