import 'package:flutter/material.dart';

class MaterialDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: new Text('Material 案例'),
          actions: <Widget>[],
        ),
        body: Row(children: [
          Material(
            color: Colors.black54,
            elevation: 10,
            child: Text("Material elevation = 10,black MaterialType.canvas"),
          ),
          Material(
            color: Colors.black54,
            type: MaterialType.card,
            elevation: 1,
            child: Text("Material elevation = 10,black MaterialType.card"),
          ),
          Material(
            color: Colors.black54,
            type: MaterialType.circle,
            elevation: 1,
            child: Text("Material elevation = 10,black MaterialType.circle"),
          ),
          Material(
            color: Colors.black54,
            type: MaterialType.button,
            elevation: 1,
            child: Text("Material elevation = 10,black MaterialType.button"),
          ),
          Material(
            color: Colors.black54,
            type: MaterialType.transparency,
            elevation: 1,
            child: Text("Material elevation = 10,black MaterialType.button"),
          ),
        ]));
  }
}
