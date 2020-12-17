[![pub package](https://img.shields.io/pub/v/stretchbox.svg)](https://pub.dartlang.org/packages/stretchbox)

# stretchbox

[`stretchbox`](https://pub.dartlang.org/packages/stretchbox) 用来管理展开子组件的``widget``.

## 使用方法

stretchbox 基础用法:

```dart
Widget buildColumnStretchedBoxWidget() {
  return ColumnStretchedBoxWidget(
    arrowImageRes: 'images/image_down_expand.png',// 箭头图片资源
    arrowContainerColor: Colors.white,// 箭头图片控件背景颜色
//      stretchState: StretchedBoxState.normal,// 默认 normal 状态，即：折叠
    normalChild: Container(// 总是展示的区域
      color: Colors.white,
      child: Column(
        children: normalList,
      ),
    ),
    expandChild: Container(// 展开才展示的区域
      color: Colors.black12,
      child: Column(
        children: [...normalList, ...expandList],
      ),
    ),
  );
}
```

stretchbox 自定义底部控件的用法:

```dart
Widget buildColumnStretchedBoxWidget() {
  return ColumnStretchedBoxWidget(
    ...
    bottomBarWidget: _buildCustomBottomWidget,// 底部控件自定义配置
  );
}
```

允许自定义设置底部控件.

```dart
Widget _buildCustomBottomWidget(
    BuildContext context, StretchedBoxViewModel stretchedBoxViewModel) {
  Widget text;
  if (stretchedBoxViewModel?.isExpand() ?? false) {
      text = Text("Click Collapse");
  } else {
      text = Text("Click Expand");
  }

  return GestureDetector(
    behavior: HitTestBehavior.opaque,
    onTap: () {
      stretchedBoxViewModel?.switchStretchMode();
    },
    child: Container(
      height: 32,
      width: double.infinity,
      color: Colors.deepPurpleAccent,
      child: Center(
        child: text,
      ),
    ),
  );
}
```

## 运行截图:

![](./screenshot/stretchbox_demo_zh.png)

查阅 [案例](https://github.com/HailouWang/AndroidGo/tree/master/flutter_demo/lib/widget/stretchbox).

![](./screenshot/stretchbox_demo_en.jpg)

## 实现思路

![](./screenshot/stretchbox_idea_small.png)