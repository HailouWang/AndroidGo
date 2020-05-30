import 'package:flutter/material.dart';

final List<Color> colorList = [
  Colors.red,
  Colors.orange,
  Colors.green,
  Colors.purple,
  Colors.blue,
  Colors.yellow,
  Colors.pink,
  Colors.teal,
  Colors.deepPurpleAccent
];

class CustomScrollViewDemo extends StatelessWidget {
  final String title;

  CustomScrollViewDemo({Key key, @required this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: CustomScrollView(
        slivers: <Widget>[
          this.renderTitle("SliveGrid"),
          SliverGrid.count(
            crossAxisCount: 3,
            children: colorList
                .map((colorItem) => Container(
                      color: colorItem,
                    ))
                .toList(),
          ),
          this.renderTitle("SliveList"),
          SliverFixedExtentList(
            delegate: SliverChildBuilderDelegate(
                (context, index) => Container(
                      color: colorList[index],
                    ),
                childCount: colorList.length),
            itemExtent: 100,
          )
        ],
      ),
    );
  }

  Widget renderTitle(String title) {
    return SliverToBoxAdapter(
      child: Padding(
        padding: EdgeInsets.symmetric(vertical: 16),
        // todo : https://www.jianshu.com/p/a675b4d66a93
        child: Text(
          title,
          textAlign: TextAlign.center,
          style: TextStyle(fontSize: 20),
        ),
      ),
    );
  }
}
