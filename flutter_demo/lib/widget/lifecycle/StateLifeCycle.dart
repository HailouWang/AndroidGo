import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

/**
 * State 生命周期
 * 链接：https://zhuanlan.zhihu.com/p/82599162
 * 链接：https://blog.csdn.net/xg1057415595/article/details/86661703
 */
class StateLifeCycle extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new StateLifeCycleImpl();
  }
}

class StateLifeCycleImpl extends State<StateLifeCycle> {
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
            child: new Text("看日志，过滤：StateLifeCycle，点击次数：${_clicked}"),
            onTap: () {
              var clicked = ++_clicked;
              print("StateLifeCycle ${clicked}");
              setState(() {
                _clicked = clicked;
              });
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
}
