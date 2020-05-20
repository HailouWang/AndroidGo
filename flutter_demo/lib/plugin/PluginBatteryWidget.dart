import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutterbatteryplugin/flutterbatteryplugin.dart';

class PluginBatteryWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new PluginBatteryState();
  }
}

class PluginBatteryState extends State<PluginBatteryWidget> {
  Flutterbatteryplugin flutterbatteryplugin = new Flutterbatteryplugin();
  var _batteryLevel = "";

  @override
  Widget build(BuildContext context) {
    _getBatteryLevel();

    return new Center(
      child: new Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          new RaisedButton(
            child: new Text('Get Battery Level'),
            onPressed: _getBatteryLevel,
          ),
          new Text(_batteryLevel),
        ],
      ),
    );
  }

  void _getBatteryLevel() async {
    var batteryLevel = await flutterbatteryplugin.getBatteryLevel();
    setState(() {
      _batteryLevel = batteryLevel;
    });
  }
}
