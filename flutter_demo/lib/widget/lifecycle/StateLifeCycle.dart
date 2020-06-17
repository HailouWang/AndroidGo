import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import '../../main.dart';

/**
 * State 生命周期
 * 链接：https://zhuanlan.zhihu.com/p/82599162
 * 链接：https://blog.csdn.net/xg1057415595/article/details/86661703
 *
 * 前后台切换：http://findsrc.com/flutter/detail/8697
 */
class StateLifeCycle extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new StateLifeCycleImpl();
  }
}

class StateLifeCycleImpl extends State<StateLifeCycle>
    with WidgetsBindingObserver {
  var _clicked = 0;

  @override
  Widget build(BuildContext context) {
    print("StateLifeCycle --> build");
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('15 Intents --> 处理来自外部应用程序传入的Intents'),
      ),
      body: new Center(
        child: new GestureDetector(
            child: new Text("看日志，过滤：StateLifeCycle，点击次数：${_clicked}、直到 6次"),
            onTap: () {
              var clicked = ++_clicked;
              print("StateLifeCycle ${clicked}");
              setState(() {
                _clicked = clicked;
              });

              if (_clicked == 6) {
                Navigator.of(context)
                    .push(new MaterialPageRoute(builder: (context) {
                  return new MaterialApp(
                    title: '11 与 Android 相比：View.animate',
                    home: new SampleAppPage11(),
                  );
                }));
              }
            }),
      ),
    );
  }

  @override
  void debugFillProperties(DiagnosticPropertiesBuilder properties) {
    print("StateLifeCycle --> debugFillProperties");
    super.debugFillProperties(properties);
  }

  @override
  void didChangeDependencies() {
    print("StateLifeCycle --> didChangeDependencies");
    super.didChangeDependencies();
  }

  @override
  void dispose() {
    print("StateLifeCycle --> dispose");
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void deactivate() {
    print("StateLifeCycle --> deactivate");
    super.deactivate();
  }

  @override
  void setState(VoidCallback fn) {
    print("StateLifeCycle --> setState");
    super.setState(fn);
  }

  @override
  void reassemble() {
    print("StateLifeCycle --> reassemble");
    super.reassemble();
  }

  @override
  void initState() {
    print("StateLifeCycle --> initState");
    super.initState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  bool get mounted {
    print("StateLifeCycle --> mounted");
    return super.mounted;
  }

  @override
  BuildContext get context {
    print("StateLifeCycle --> context");
    return super.context;
  }

  @override
  void didUpdateWidget(StateLifeCycle oldWidget) {
    print("StateLifeCycle --> didUpdateWidget");
    super.didUpdateWidget(oldWidget);
  }

  @override
  StateLifeCycle get widget {
    print("StateLifeCycle --> widget");
    return super.widget;
  }

  // WidgetsBindingObserver start
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    print(
        "StateLifeCycle --> =======> didChangeAppLifecycleState state : ${state}");
    switch (state) {
      case AppLifecycleState.inactive: // 处于这种状态的应用程序应该假设它们可能在任何时候暂停。
        break;
      case AppLifecycleState.resumed: // 应用程序可见，前台
        break;
      case AppLifecycleState.paused: // 应用程序不可见，后台
        break;
      case AppLifecycleState.detached: // 申请将暂时暂停
        break;
    }
  }

  @override
  Future<bool> didPopRoute() {
    print("StateLifeCycle --> =======> didPopRoute");
  }

  @override
  Future<bool> didPushRoute(String route) {
    print("StateLifeCycle --> =======> didPushRoute");
  }

  @override
  void didChangeMetrics() {
    print("StateLifeCycle --> =======> didChangeMetrics");
  }

  @override
  void didChangeTextScaleFactor() {
    print("StateLifeCycle --> =======> didChangeTextScaleFactor");
  }

  @override
  void didChangePlatformBrightness() {
    print("StateLifeCycle --> =======> didChangePlatformBrightness");
  }

  @override
  void didChangeLocales(List<Locale> locale) {
    print("StateLifeCycle --> =======> didChangeLocales locale : ${locale}");
  }

  @override
  void didHaveMemoryPressure() {
    print("StateLifeCycle --> =======> didHaveMemoryPressure");
  }

  @override
  void didChangeAccessibilityFeatures() {
    print("StateLifeCycle --> =======> didChangeAccessibilityFeatures");
  }

// WidgetsBindingObserver end
}
