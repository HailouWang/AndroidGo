import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

/*共享数据*/
class ShareDataWidget extends InheritedWidget {
  ShareDataWidget({@required this.data, Widget child}) : super(child: child);

  final int data; //需要在子树中共享的数据，保存点击次数

  //定义一个便捷方法，方便子树中的widget获取共享数据
  static ShareDataWidget of(BuildContext context) {
    return context.inheritFromWidgetOfExactType(ShareDataWidget);
//    return context.ancestorInheritedElementForWidgetOfExactType(ShareDataWidget).widget;
  }

  //该回调决定当data发生变化时，是否通知子树中依赖data的Widget
  @override
  bool updateShouldNotify(ShareDataWidget old) {
    print("InheritedWidget7_2 ShareDataWidget updateShouldNotify old : " +
        old.toStringDeep());

    //如果返回true，则子树中依赖(build函数中有调用)本widget
    //的子widget的`state.didChangeDependencies`会被调用
    return old.data != data;
  }
}

/*数据使用者*/
class _TestWidget extends StatefulWidget {
  @override
  __TestWidgetState createState() => new __TestWidgetState(data: data);

  _TestWidget({@required this.data});

  final int data;
}

class __TestWidgetState extends State<_TestWidget> {
  @override
  Widget build(BuildContext context) {
    print("InheritedWidget7_2 __TestWidgetState build");
    //使用InheritedWidget中的共享数据
    return Text(ShareDataWidget.of(context).data.toString());
//    return Text("text" + data.toString());
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    //父或祖先widget中的InheritedWidget改变(updateShouldNotify返回true)时会被调用。
    //如果build中没有依赖InheritedWidget，则此回调不会被调用。
    print("InheritedWidget7_2 __TestWidgetState Dependencies change");
  }

  __TestWidgetState({@required this.data}) {
    print("InheritedWidget7_2 __TestWidgetState contructor data : " +
        data.toString());
  }

  final int data;
}

/*上层UI*/
class InheritedWidgetTestRoute extends StatefulWidget {
  @override
  _InheritedWidgetTestRouteState createState() =>
      new _InheritedWidgetTestRouteState();
}

class _InheritedWidgetTestRouteState extends State<InheritedWidgetTestRoute> {
  int count = 0;

  @override
  Widget build(BuildContext context) {
    print("InheritedWidget7_2 _InheritedWidgetTestRouteState build count : " +
        count.toString());

    return Center(
      child: ShareDataWidget(
        //使用ShareDataWidget
        data: count,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(bottom: 20.0),
              child: _TestWidget(data: count), //子widget中依赖ShareDataWidget
            ),
            RaisedButton(
              child: Text("Increment"),
              //每点击一次，将count自增，然后重新build,ShareDataWidget的data将被更新
              onPressed: () => setState(() => ++count),
            )
          ],
        ),
      ),
    );
  }
}
