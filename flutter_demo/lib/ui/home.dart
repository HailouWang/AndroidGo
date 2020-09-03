import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutterdemo/plugin/PluginBatteryWidget.dart';
import 'package:flutterdemo/provider/ProviderDemo.dart';
import 'package:flutterdemo/simple_page_widgets.dart';
import 'package:flutterdemo/ui/demos/DemoList.dart';
import 'package:flutterdemo/ui/mine/MineList.dart';
import 'package:flutterdemo/ui/special/SpecialList.dart';

class HomeUI extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomeUIState();
}

class _HomeUIState extends State<HomeUI> {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
//    final wordPair = new WordPair.random();
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
          // This is the theme of your application.
          //
          // Try running your application with "flutter run". You'll see the
          // application has a blue toolbar. Then, without quitting the app, try
          // changing the primarySwatch below to Colors.green and then invoke
          // "hot reload" (press "r" in the console where you ran "flutter run",
          // or simply save your changes to "hot reload" in a Flutter IDE).
          // Notice that the counter didn't reset back to zero; the application
          // is not restarted.
          primarySwatch: Colors.blue,
          primaryColor: Colors.white),
      builder: FlutterBoost.init(postPush: _onRoutePushed),
      home: Container(color: Colors.white),
      //注册路由监听器
      navigatorObservers: [
        //这个监听器是个集合，可根据不同需求对路由做不同的设置
        new MyNavigatorObserver(),
      ],
      onGenerateRoute: (routeSettings) {
        print("Main OnGenerateRoute routeSettings : ${routeSettings}");
      },
    );
  }

  void _onRoutePushed(
    String pageName,
    String uniqueId,
    Map<String, dynamic> params,
    Route<dynamic> route,
    Future<dynamic> _,
  ) {}

  @override
  void initState() {
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
    });
    FlutterBoost.singleton.addBoostNavigatorObserver(MyNavigatorObserver());
  }
}

class TabContainer extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TabContainerState();
  }
}

class TabContainerState extends State<TabContainer>
    with SingleTickerProviderStateMixin {
  TabController tabController;

  @override
  void initState() {
    tabController = new TabController(length: 3, vsync: this);
  }

  @override
  void dispose() {
    tabController?.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: new Scaffold(
      body: TabBarView(
        controller: tabController,
        children: <Widget>[DemoList(), SpecialList(), MineList()],
      ),
      bottomNavigationBar: new Material(
        color: Colors.white,
        child: TabBar(
          controller: tabController,
          labelColor: Colors.deepPurpleAccent,
          unselectedLabelColor: Colors.black26,
          tabs: <Widget>[
            new Tab(text: "案例集合", icon: new Icon(Icons.view_list)),
            new Tab(text: "Flutter 原生控件样式", icon: new Icon(Icons.style)),
            new Tab(text: "我的", icon: Icon(Icons.minimize))
          ],
        ),
      ),
    ));
  }
}

class MyNavigatorObserver extends NavigatorObserver {
  @override
  NavigatorState get navigator {}

  @override
  void didStopUserGesture() {
    print('MyNavigatorObserver ----------didStopUserGesture-----------');
    print('MyNavigatorObserver----------end-----------');
  }

  @override
  void didStartUserGesture(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('MyNavigatorObserver ----------StartUserGesture-----------');
    print('MyNavigatorObserver 当前活动的路由：${route.settings}');
    print('MyNavigatorObserver 替换活动的路由：${previousRoute?.settings}');
    print('MyNavigatorObserver ----------end-----------');
  }

  @override
  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    print('MyNavigatorObserver ----------replace-----------');
    print('MyNavigatorObserver 当前活动的路由：${oldRoute.settings}');
    print('MyNavigatorObserver 替换活动的路由：${newRoute?.settings}');
    print('MyNavigatorObserver ----------end-----------');
  }

  @override
  void didRemove(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('MyNavigatorObserver ----------remove-----------');
    print('MyNavigatorObserver 当前活动的路由：${route.settings}');
    print('MyNavigatorObserver 先前活动的路由：${previousRoute?.settings}');
    print('MyNavigatorObserver ----------end-----------');
  }

  @override
  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('MyNavigatorObserver ----------pop-----------');
    print('MyNavigatorObserver 当前活动的路由：${route.settings}');
    print('MyNavigatorObserver 先前活动的路由：${previousRoute?.settings}');
    print('MyNavigatorObserver ----------end-----------');
  }

  @override
  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('MyNavigatorObserver ----------push-----------');
    print('MyNavigatorObserver 当前活动的路由：${route.settings}');
    print('MyNavigatorObserver 先前活动的路由：${previousRoute?.settings}');
    print('MyNavigatorObserver ----------end-----------');
  }
}
