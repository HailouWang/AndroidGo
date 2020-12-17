import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'dart:math' as math;
import 'package:stretchbox/model/stretched_box_state.dart';
import 'package:stretchbox/vm/stretched_box_viewmodel.dart';

import 'custom_stretched_box.dart';

// 上下布局的 Widget
// ignore: must_be_immutable
class ColumnStretchedBoxWidget extends StretchedBoxWidget {
  /// 总是展示的控件
  final Widget normalChild;
  /// 折叠控件，展开时才会看到
  final Widget expandChild;
  /// 底部组件 箭头图片资源
  final String arrowImageRes;
  /// 底部组件 背景颜色
  Color arrowContainerColor;

  /// Constructs 构造
  ColumnStretchedBoxWidget({
    this.normalChild,
    this.expandChild,
    this.arrowContainerColor = Colors.transparent,
    this.arrowImageRes = 'images/ic_down_expand.png',
    StretchedBoxState stretchState = StretchedBoxState.normal,
    Key key,
  }) : super(
            key: key,
            normalChild: normalChild,
            expandChild: expandChild,
            stretchState: stretchState,
            bottomBarWidget: (BuildContext context,
                StretchedBoxViewModel stretchboxViewModel) {
              double angle = 0.0;
              if (stretchboxViewModel.isExpand()) {
                angle = math.pi;
              }

              return GestureDetector(
                behavior: HitTestBehavior.opaque,
                onTap: () {
                  if (kDebugMode) {
                    print("ColumnStretchedBox ArrowBottomWidget clicked!");
                  }
                  stretchboxViewModel?.switchStretchMode();
                },
                child: Container(
                  height: 30,
                  color: arrowContainerColor,
                  width: double.infinity,
                  alignment: AlignmentDirectional.center,
                  child: Transform.rotate(
                    angle: angle,
                    child: Image.asset(
                      arrowImageRes,
                      width: 12,
                      height: 6,
                    ),
                  ),
                ),
              );
            });
}
