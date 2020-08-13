import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'model/Counter.dart';

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print("hlwang SecondPage value : ${Provider.of<Counter>(context).count} ");
    return Scaffold(
      appBar: AppBar(
        title: Text("SecondPage"),
      ),
      body: Center(
        child: Text("${Provider.of<Counter>(context).count}"),
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