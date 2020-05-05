// 参考文章：https://www.jianshu.com/p/7085019c4228
//

import 'package:flutterdemo/provider/HomePage.dart';
import 'package:flutterdemo/provider/model/Counter.dart';
import 'package:provider/provider.dart';
import 'package:flutter/cupertino.dart';

// https://loveky.github.io/2018/07/18/how-flutter-inheritedwidget-works/
// https://juejin.im/post/5d00a84fe51d455a2f22023f
// https://www.jianshu.com/p/7085019c4228
// https://blog.csdn.net/unicorn97/article/details/99877867
class ProviderDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<Counter>.value(
        notifier: Counter(0),
        child: HomePage());
  }
}
