import 'package:collapsebox/model/collapsebox_state.dart';
import 'package:collapsebox/vm/collapsebox_viewmodel.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'dart:math' as math;

import 'custom_collapsebox.dart';

// 上下布局的 Widget
// ignore: must_be_immutable
class ColumnCollapseBoxWidget extends CollapseBoxWidget {
  /// 总是展示的控件
  final Widget alwaysShowChild;

  /// 折叠控件，展开时才会看到
  final Widget collapsedChild;

  /// 底部组件 箭头图片资源
  final String bottomArrowImageRes;

  /// 底部组件 背景颜色，箭头区域
  Color bottomBarColor;

  /// Constructs 构造
  ColumnCollapseBoxWidget({
    this.alwaysShowChild,
    this.collapsedChild,
    this.bottomBarColor = Colors.transparent,
    this.bottomArrowImageRes = 'images/ic_down_expand.png',
    CollapseBoxState collapseboxState = CollapseBoxState.normal,
    Key key,
  }) : super(
            key: key,
            alwaysShowChild: alwaysShowChild,
            collapsedChild: collapsedChild,
            collapseboxState: collapseboxState,
            bottomBarWidget: (BuildContext context,
                CollapseboxBoxViewModel collapseboxViewModel) {
              double angle = 0.0;
              if (collapseboxViewModel.isExpand()) {
                angle = math.pi;
              }

              return GestureDetector(
                behavior: HitTestBehavior.opaque,
                onTap: () {
                  if (kDebugMode) {
                    print("ColumnStretchedBox ArrowBottomWidget clicked!");
                  }
                  collapseboxViewModel?.switchCollapseBoxMode();
                },
                child: Container(
                  height: 30,
                  color: bottomBarColor,
                  width: double.infinity,
                  alignment: AlignmentDirectional.center,
                  child: Transform.rotate(
                    angle: angle,
                    child: Image.asset(
                      bottomArrowImageRes,
                      width: 12,
                      height: 6,
                    ),
                  ),
                ),
              );
            });
}
