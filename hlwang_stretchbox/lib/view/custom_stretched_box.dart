library flutter_udstretchbox;

import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';
import 'package:stretchbox/model/stretched_box_state.dart';
import 'package:stretchbox/vm/stretched_box_viewmodel.dart';

/// 视图 StretchboxWidget.
/// 提供 状态管理，需要外部传入：正常场景下的Widget以及展开状态下的Widget
// ignore: must_be_immutable
class StretchedBoxWidget extends StatelessWidget {
  /// 折叠控件 ViewModel
  StretchedBoxViewModel stretchboxViewModel;
  /// 总是展示的控件
  final Widget normalChild;
  /// 折叠控件，展开时才会看到
  final Widget expandChild;
  /// 自定义方法，对外提供底部配置的方法
  Widget Function(BuildContext, StretchedBoxViewModel) bottomBarWidget;

  /// Constructs 构造
  StretchedBoxWidget({
    Key key,
    this.normalChild,
    this.expandChild,
    this.bottomBarWidget,
    StretchedBoxState stretchState = StretchedBoxState.normal,
  }) : super(key: key) {
    stretchboxViewModel = StretchedBoxViewModel(stretchState);
  }

  /// 原生生命周期方法，生成本页面Widget视图树
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => stretchboxViewModel,
      child: Column(
        children: <Widget>[
          this.normalChild,
          Selector<StretchedBoxViewModel, StretchedBoxState>(
              selector: (context, value) => stretchboxViewModel?.stretchState,
              builder: (BuildContext context, value, Widget child) {
                return _buildContentWidget(context, stretchboxViewModel);
              }),
        ],
      ),
    );
  }

  /// 生成底部控件，默认返回Null，允许子类定义
  Widget buildBottomBarWidget(
      BuildContext context, StretchedBoxViewModel stretchboxViewModel) {
    return null;
  }

  /// 私有方法，不开放给外部，声场本控件视图逻辑空知
  Widget _buildContentWidget(
      BuildContext context, StretchedBoxViewModel stretchboxViewModel) {
    List<Widget> widgets = [];

    if (stretchboxViewModel == null) {
      return null;
    }

    if (stretchboxViewModel.isExpand()) {
      widgets.add(this.expandChild);
    }

    Widget bottomWidget;
    if (bottomBarWidget != null) {
      bottomWidget = bottomBarWidget(context, stretchboxViewModel) ??
          buildBottomBarWidget(context, stretchboxViewModel);
    }

    if (bottomWidget != null) {
      widgets.add(bottomWidget);
    }

    return Column(
      children: widgets,
    );
  }
}
