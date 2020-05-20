import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class FlexWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _homeState();
  }
}

class _homeState extends State<FlexWidget> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("title"),
        centerTitle: true,
      ),
      body: _body(),
    );
  }
}

Widget _body() {
  return Column(
    children: <Widget>[
      //Flex的两个子widget按1：2来占据水平空间
      Flex(
        direction: Axis.horizontal,
        children: <Widget>[
          Expanded(
            flex: 1,
            child: Container(
              height: 80.0,
              color: Colors.red,
            ),
          ),
          Expanded(
            flex: 2,
            child: Container(
              height: 80.0,
              color: Colors.green,
            ),
          ),
        ],
      ),
      Padding(
        padding: const EdgeInsets.only(top: 20.0),
        child: SizedBox(
          height: 100.0,
          //Flex的三个子widget，在垂直方向按2：1：1来占用100像素的空间
          child: Flex(
            direction: Axis.vertical,
            children: <Widget>[
              Expanded(
                flex: 2,
                child: Container(
                  color: Colors.red,
                ),
              ),
              Spacer(
                //Spacer的功能是占用指定比例的空间，实际上它只是Expanded的一个包装
                flex: 1,
              ),
              Expanded(
                flex: 1,
                child: Container(
                  color: Colors.green,
                ),
              ),
            ],
          ),
        ),
      ),
    ],
  );
}
