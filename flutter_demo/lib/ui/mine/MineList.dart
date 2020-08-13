import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MineList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: AppBar(
          title: new Text('Flutter 案例'),
          actions: <Widget>[],
        ),
        body: Text("我的"));
  }
}
