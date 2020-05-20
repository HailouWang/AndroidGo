import 'dart:async';

import 'package:flutter/material.dart';
import 'package:oktoast/oktoast.dart';

class OKToastWidget extends StatefulWidget {
  OKToastWidget({Key key, this.title}) : super(key: key);

// This widget is the home page of your application. It is stateful, meaning
// that it has a State object (defined below) that contains fields that affect
// how it looks.

// This class is the configuration for the state. It holds the values (in this
// case the title) provided by the parent (in this case the App widget) and
// used by the build method of the State. Fields in a Widget subclass are
// always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<OKToastWidget> {
  int _counter = 0;

  void _showToast() {
    showToast("msg");
  }

  @override
  Widget build(BuildContext context) {
    return OKToast(
        dismissOtherOnShow: true,
        child: new MaterialApp(
          title: 'Flutter Demo',
          theme: new ThemeData(
            // This is the theme of your application.
            //
            // Try running your application with "flutter run". You'll see the
            // application has a blue toolbar. Then, without quitting the app, try
            // changing the primarySwatch below to Colors.green and then invoke
            // "hot reload" (press "r" in the console where you ran "flutter run",
            // or press Run > Flutter Hot Reload in IntelliJ). Notice that the
            // counter didn't reset back to zero; the application is not restarted.
            primarySwatch: Colors.blue,
          ),
          home: ListView(
            children: <Widget>[
              RaisedButton(
                child: Text('文字toast'),
                onPressed: _showToast,
              ),
              RaisedButton(
                child: Text('自定义Widget Toast'),
                onPressed: _showCustomWidgetToast,
              ),
              RaisedButton(
                child: Text('ToastHelper '),
                onPressed: () => ToastHelper.showToast(context, "toast helper"),
              ),
            ],
          ),
        ));
  }

  void _showCustomWidgetToast() {
    var w = Center(
      child: Container(
        padding: const EdgeInsets.all(5),
        color: Colors.black.withOpacity(0.7),
        child: Row(
          children: <Widget>[
            Icon(
              Icons.add,
              color: Colors.white,
            ),
            Text(
              '添加成功',
              style: TextStyle(color: Colors.white),
            ),
          ],
          mainAxisSize: MainAxisSize.min,
        ),
      ),
    );
    showToastWidget(w);
  }
}

class ToastHelper {
  static void showToast(BuildContext context, String text) {
    const style = TextStyle(color: Colors.white, fontSize: 14.0);

    Widget widget = Center(
      child: Material(
        child: Container(
          color: Colors.black.withOpacity(0.5),
          padding: const EdgeInsets.symmetric(vertical: 5.0, horizontal: 10.0),
          child: Text(
            text,
            style: style,
          ),
        ),
      ),
    );
    var entry = OverlayEntry(
      builder: (_) => widget,
    );

    Overlay.of(context).insert(entry);

    Timer(const Duration(seconds: 2), () {
      entry?.remove();
    });
  }
}
