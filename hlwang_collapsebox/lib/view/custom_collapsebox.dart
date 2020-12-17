library flutter_udstretchbox;

import 'package:collapsebox/model/collapsebox_state.dart';
import 'package:collapsebox/vm/collapsebox_viewmodel.dart';
import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';

/// 视图 StretchboxWidget.
/// 提供 状态管理，需要外部传入：正常场景下的Widget以及展开状态下的Widget
// ignore: must_be_immutable
class CollapseBoxWidget extends StatelessWidget {
  /// 折叠控件 ViewModel
  CollapseboxBoxViewModel collapseboxViewModel;

  /// 总是展示的控件
  final Widget alwaysShowChild;

  /// 折叠控件，展开时才会看到
  final Widget collapsedChild;

  /// 自定义方法，对外提供底部配置的方法
  Widget Function(BuildContext, CollapseboxBoxViewModel) bottomBarWidget;

  /// Constructs 构造
  CollapseBoxWidget({
    Key key,
    this.alwaysShowChild,
    this.collapsedChild,
    this.bottomBarWidget,
    CollapseBoxState collapseboxState = CollapseBoxState.normal,
  }) : super(key: key) {
    collapseboxViewModel = CollapseboxBoxViewModel(collapseboxState);
  }

  /// 原生生命周期方法，生成本页面Widget视图树
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => collapseboxViewModel,
      child: Column(
        children: <Widget>[
          this.alwaysShowChild,
          Selector<CollapseboxBoxViewModel, CollapseBoxState>(
              selector: (context, value) => collapseboxViewModel?.collapseboxState,
              builder: (BuildContext context, value, Widget child) {
                return _buildContentWidget(context, collapseboxViewModel);
              }),
        ],
      ),
    );
  }

  /// 生成底部控件，默认返回Null，允许子类定义
  Widget buildBottomBarWidget(
      BuildContext context, CollapseboxBoxViewModel collapseboxViewModel) {
    return null;
  }

  /// 私有方法，不开放给外部，声场本控件视图逻辑空知
  Widget _buildContentWidget(
      BuildContext context, CollapseboxBoxViewModel collapseboxViewModel) {
    List<Widget> widgets = [];

    if (collapseboxViewModel == null) {
      return null;
    }

    if (collapseboxViewModel.isExpand()) {
      widgets.add(this.collapsedChild);
    }

    Widget bottomWidget;
    if (bottomBarWidget != null) {
      bottomWidget = bottomBarWidget(context, collapseboxViewModel) ??
          buildBottomBarWidget(context, collapseboxViewModel);
    }

    if (bottomWidget != null) {
      widgets.add(bottomWidget);
    }

    return Column(
      children: widgets,
    );
  }
}
