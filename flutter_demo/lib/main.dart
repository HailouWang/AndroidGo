import 'dart:convert';
import 'dart:isolate';

import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter/services.dart';
import 'package:flutterdemo/app/Application.dart';
import 'package:flutterdemo/media/MediaQueryDataWidget.dart';
import 'package:flutterdemo/plugin/PluginBatteryWidget.dart';
import 'package:flutterdemo/provider/ProviderDemo.dart';
import 'package:flutterdemo/provider/common/AppSettingsModel.dart';
import 'package:flutterdemo/provider/common/UserInfoModel.dart';
import 'package:flutterdemo/router/routes.dart';
import 'package:flutterdemo/widget/NativeViewDemo.dart';
import 'package:flutterdemo/widget/flex/FlexWidget.dart';
import 'package:flutterdemo/widget/lifecycle/StateLifeCycle.dart';
import 'package:flutterdemo/widget/oktoast/OKToastWidget.dart';
import 'package:flutterdemo/widget/sliver/SliverAppBarDemo.dart';
import 'package:flutterdemo/widget/sliver/SliverPersistentHeaderShrinkOffsetDemo.dart';
import 'package:flutterdemo/widget/willpopscope/WillPopScopeWidget.dart';
import 'package:http/http.dart' as http;
import 'package:oktoast/oktoast.dart';
import 'package:provider/provider.dart';

import 'ui/home.dart';
import 'widget/sliver/CustomScrollViewDemo.dart';
import 'widget/sliver/SliverPersistentHeaderStickyDemo.dart';

// => 单行函数或者方法的简写
void main() => runApp(MyApp());

// TopLevel
// 放置全局状态 数据，任何组件都依赖的部分
class MyApp extends StatelessWidget {
  MyApp() {
    final router = Router();
    Routers.configureRoutes(router);
    Application.router = router;
  }

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_context) => AppSettingsModel()),
          ChangeNotifierProvider(
            create: (_context) {
              print("hlwang MyApp UserInfoModel ");
              return new UserInfoModel();
            },
          )
        ],
        child: OKToast(
          child: MediaQuery(
            data: MediaQueryData.fromWindow(WidgetsBinding.instance.window),
            child: HomeUI(),
          ),
        ));
  }
}
