import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'CustomScrollViewDemo.dart';

class SliverAppBarDemo extends StatelessWidget {
  final String title;

  SliverAppBarDemo({Key key, this.title}) : super(key: key) {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        // todo 学习资料： https://blog.csdn.net/qq_18948359/article/details/82181475
        body: CustomScrollView(
      slivers: <Widget>[
        SliverAppBar(
          floating: true,
          snap: true,
          pinned: true,
          expandedHeight: 250,
          elevation: 0,
          flexibleSpace: FlexibleSpaceBar(
            title: Text(this.title),
            background: Image.network(
              "http://img1.mukewang.com/5c18cf540001ac8206000338.jpg",
              fit: BoxFit.cover,
            ),
          ), //空间大小可变的组件
        ),
        SliverFixedExtentList(
          delegate: SliverChildBuilderDelegate(
            (context, index) {
              final isOdd = index % 2 == 1;
              return Container(
                alignment: Alignment.center,
                color: isOdd ? Colors.white : Color(0xFFEAEAEA),
                child: Text(
                  index.toString(),
                  style: TextStyle(
                    fontSize: 30,
                    fontWeight: FontWeight.w500,
                    color: Colors.black,
                  ),
                ),
              );
            },
            childCount: 50,
          ),
          itemExtent: 100,
        )
      ],
    ));
  }
}
