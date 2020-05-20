import 'package:flutter/widgets.dart';

class MyNavigatorObseeerver extends NavigatorObserver {
  @override
  NavigatorState get navigator {}

  @override
  void didStopUserGesture() {
    print('----------didStopUserGesture-----------');
    print('----------end-----------');
  }

  @override
  void didStartUserGesture(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('----------StartUserGesture-----------');
    print('当前活动的路由：${route.settings}');
    print('替换活动的路由：${previousRoute?.settings}');
    print('----------end-----------');
  }

  @override
  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    print('----------replace-----------');
    print('当前活动的路由：${oldRoute.settings}');
    print('替换活动的路由：${newRoute?.settings}');
    print('----------end-----------');
  }

  @override
  void didRemove(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('----------remove-----------');
    print('当前活动的路由：${route.settings}');
    print('先前活动的路由：${previousRoute?.settings}');
    print('----------end-----------');
  }

  @override
  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('----------pop-----------');
    print('当前活动的路由：${route.settings}');
    print('先前活动的路由：${previousRoute?.settings}');
    print('----------end-----------');
  }

  @override
  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {
    print('----------push-----------');
    print('当前活动的路由：${route.settings}');
    print('先前活动的路由：${previousRoute?.settings}');
    print('----------end-----------');
  }
}
