import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NativeViewDemo extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _NativeViewDemoState();
  }
}

class _NativeViewDemoState extends State<NativeViewDemo> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: new AppBar(
        title: new Text('15 Intents --> 处理来自外部应用程序传入的Intents'),
      ),
      body: new Center(child: AndroidView(viewType: "CustomAndroidView")),
    );
  }
}
