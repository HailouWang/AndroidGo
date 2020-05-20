// https://blog.csdn.net/ruoshui_t/article/details/95456751
import 'package:flutter/cupertino.dart';

class MediaQueryDataWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new MediaQueryDataState();
  }
}

class MediaQueryDataState extends State<MediaQueryDataWidget> {
  @override
  Widget build(BuildContext context) {
    //屏幕大小
    Size mSize = MediaQuery.of(context).size;

    print("hlwang MediaQueryDataState 屏幕大小：" + mSize.toString());

    //密度
    double mRatio = MediaQuery.of(context).devicePixelRatio;
    //设备像素
    double width = mSize.width * mRatio;
    double height = mSize.height * mRatio;

    print("hlwang MediaQueryDataState 密度：" + mRatio.toString());
    print("hlwang MediaQueryDataState 宽度：" + width.toString());
    print("hlwang MediaQueryDataState 高度：" + height.toString());

    // 上下边距 （主要用于 刘海  和  内置导航键）
    double topPadding = MediaQuery.of(context).padding.top;
    double bottomPadding = MediaQuery.of(context).padding.bottom;

    print("hlwang MediaQueryDataState 上边距：" + topPadding.toString());
    print("hlwang MediaQueryDataState 下边距：" + bottomPadding.toString());

    double textScaleFactor = MediaQuery.of(context).textScaleFactor;
    Brightness platformBrightness = MediaQuery.of(context).platformBrightness;
    EdgeInsets viewInsets = MediaQuery.of(context).viewInsets;
    EdgeInsets padding = MediaQuery.of(context).padding;
    bool alwaysUse24HourFormat = MediaQuery.of(context).alwaysUse24HourFormat;
    bool accessibleNavigation = MediaQuery.of(context).accessibleNavigation;
    bool invertColors = MediaQuery.of(context).invertColors;
    bool disableAnimations = MediaQuery.of(context).disableAnimations;

    print("hlwang MediaQueryDataState textScaleFactor：" +
        textScaleFactor.toString());
    print("hlwang MediaQueryDataState platformBrightness：" +
        platformBrightness.toString());
    print("hlwang MediaQueryDataState viewInsets：" + viewInsets.toString());
    print("hlwang MediaQueryDataState viewInsets：" + viewInsets.toString());
    print("hlwang MediaQueryDataState padding：" + padding.toString());
    print("hlwang MediaQueryDataState alwaysUse24HourFormat：" +
        alwaysUse24HourFormat.toString());
    print("hlwang MediaQueryDataState accessibleNavigation：" +
        accessibleNavigation.toString());
    print("hlwang MediaQueryDataState invertColors：" + invertColors.toString());
    print("hlwang MediaQueryDataState disableAnimations：" +
        disableAnimations.toString());

    return new Text("查看日志，过滤：hlwang");
  }
}
