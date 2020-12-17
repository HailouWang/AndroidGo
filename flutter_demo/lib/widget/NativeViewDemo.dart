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
        title: new Text('31 借助原生视图能力，android 绘制'),
      ),
      body: new Center(child: AndroidView(viewType: "CustomAndroidView")),
    );
  }
}
