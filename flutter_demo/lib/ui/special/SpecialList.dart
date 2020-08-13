import 'dart:convert';

import 'package:fluro/fluro.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutterdemo/app/Application.dart';

import 'MaterialDemo.dart';

class SpecialList extends StatelessWidget {
  final _ListDatas = <ListItem>[];

  // 01 Material
  SpecialList() {
    _ListDatas.add(
        new ListItem("01 Material", MaterialPageRoute(builder: (context) {
          return MaterialDemo();
        })));
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        home: new Scaffold(
            appBar: AppBar(
              title: new Text('Flutter 案例'),
              actions: <Widget>[],
            ),
            body: new ListView.builder(
              padding: const EdgeInsets.all(16.0),
              itemBuilder: (context, i) {
                if (i.isOdd) return new Divider();

                final index = i ~/ 2;

                if (index >= _ListDatas.length) {
                  return null;
                }

                return _buildRow(context, _ListDatas[index], index);
              },
            )));
  }

  void _onClick(BuildContext context, ListItem listItem, int index) {
//    Application.router.navigateTo(
//        context,
//        listItem?.routerPath +
//            "?index=${index}+listItem=${jsonEncode(Utf8Encoder().convert(listItem.toString()))}",
//        transition: TransitionType.inFromRight);

    Navigator.of(context).push(listItem.route);
  }

  final _biggerFont = const TextStyle(fontSize: 18.0);

  Widget _buildRow(BuildContext context, ListItem listItem, int index) {
    return new ListTile(
      title: new Text(
        listItem?.title,
        style: _biggerFont,
      ),
      onTap: () {
        _onClick(context, listItem, index);
      },
    );
  }
}

class ListItem {
  String title;
  String routerPath;

  Route route;

  ListItem(this.title, Route<dynamic> this.route) {}
}
