import 'package:flutter/cupertino.dart';

/// https://blog.csdn.net/mqdxiaoxiao/article/details/102560754
class FutureDemoWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _FutureDemoWidget();
}

class _FutureDemoWidget extends State<FutureDemoWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 16),
      child: Column(
        children: <Widget>[
          SizedBox(
            height: 16,
          ),
          GestureDetector(
            onTap: () {
              FutureWithThen();
            },
            child: Text("Future then"),
          )
        ],
      ),
    );
  }

  FutureWithThen() {
    Future.delayed(new Duration(seconds: 2), () {
      return "Hello World!";
    }).then((data) {
      print(data);
    });
  }

  /// 注意，重点在于，每一步的Then 都要 return，否则，链式调用的下方，无法收到
  FlutterWithThenAndThen() {
    Future.delayed(new Duration(seconds: 2), () {
      return 'Hello';
    }).then((data) {
      return data + ' World';
    }).then((data) {
      return data + ' and';
    }).then((data) {
      return data + ' 野猿新一!';
    }).then((data) {
      print(data);
    }).catchError((e) {
      print(e);
    });
  }

  FutureWithCatchError() {
    Future.delayed(new Duration(seconds: 2), () {
      // 2秒后抛出一个异常
      throw AssertionError("Error");
    }).then((data) {
      // 执行成功走到这里
      print("success");
    }).catchError((e) {
      // 执行失败走到这里
      print(e);
    });
  }

  /// 其实不止catchError方法可以捕获异常，在then方法中还提供了一个可选参数onError，当发生异常的时候，会走到onError参数所传入的方法
  FutureWithOnError() {
    Future.delayed(new Duration(seconds: 2), () {
      // 2秒后抛出一个异常
      throw AssertionError("Error");
    }).then((value) {
      print("success1 ${value} ");
    }).then((data) {
      // 正常执行后回调该方法
      print("success2 ${data} ");
    }, onError: (e) {
      // 发生异常后回调该方法
      print(e);
    });
  }

  /// 若同时设置then方法的onError参数和调用catchError方法，当发生异常时程序会走到哪
  /// 可以看到当同时设置onError和catchError的时候，当发生异常时，程序只会走onError，而不会走到catchError
  FutureWithOnErrorCatchError() {
    Future.delayed(new Duration(seconds: 2), () {
      throw AssertionError("Error");
    }).then((data) {
      print("success");
    }, onError: (e) {
      print('onError ' + e.toString());
    }).catchError((e) {
      print('catchError ' + e.toString());
    });
  }

  /// app开发中网络请求时经常在请求前先弹出一个加载对话框，当请求结束时不管成功失败都要关闭对话框。
/// 这里有两种方法来关闭对话框，一种是在then和catchError(或者onError)中都写一段关闭的代码
/// 另一种是通过在whenComplete里写一段关闭的代码，不管程序执行成功与否，都会走到whenComplete里面
FutureWithOnComplete(){
  Future.delayed(new Duration(seconds: 2),(){
    return 'Hello';
  }).then((data){
    return data + ' World!';
  }).then((data){
    //执行成功走到这里
    print(data);
  }).catchError((e){
    //执行失败走到这里
    print(e);
  }).whenComplete((){
    //无论成功或失败都会走到这里
    print('whenComplete');
  });
}




}
