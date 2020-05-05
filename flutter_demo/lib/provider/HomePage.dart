import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';

import 'SecondPage.dart';
import 'model/Counter.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Home"),
        actions: <Widget>[
          FlatButton(
            child: Text("下一页"),
            onPressed: () =>
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return SecondPage();
                })),
          ),
        ],
      ),
      body: Center(
//        child: Text("${Provider.of<Counter>(context).count}"),
        child:Consumer<Counter>(builder: (context,counter,child) => Text("${counter.count}"),)
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Provider.of<Counter>(context).add();
        },
        child: Icon(Icons.add),
      ),
    );
  }
}