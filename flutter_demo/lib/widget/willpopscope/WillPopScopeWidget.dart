import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/**
 * 链接：https://www.jianshu.com/p/858ea277675f
 * 链接：https://www.jianshu.com/p/befe02d60944
 */
class WillPopScopeWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _HomePageState();
  }
}

class _HomePageState extends State<WillPopScopeWidget> {
  int last = 0;

  Future<bool> doubleClickBack() {
    int now = DateTime.now().millisecond;
    if (now - last > 800) {
      last = DateTime.now().millisecond;
      return Future.value(false);
    } else {
      return Future.value(true);
    }
  }

  Future<bool> _onWillPop() {
    return showDialog(
          context: context,
          builder: (context) => new AlertDialog(
            title: new Text('Are you sure?'),
            content: new Text('Do you want to exit an App'),
            actions: <Widget>[
              new FlatButton(
                onPressed: () => Navigator.of(context).pop(false),
                child: new Text('No'),
              ),
              new FlatButton(
                onPressed: () => Navigator.of(context).pop(true),
                child: new Text('Yes'),
              ),
            ],
          ),
        ) ??
        false;
  }

  @override
  Widget build(BuildContext context) {
    return new WillPopScope(
      onWillPop: _onWillPop,
      child: new Scaffold(
        appBar: new AppBar(
          title: new Text("Home Page"),
        ),
        body: new Center(
          child: new Text("Home Page"),
        ),
      ),
    );
  }
}
