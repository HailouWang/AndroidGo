library flutter_udstretchbox;

import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';
import 'package:stretchbox/model/stretched_box_state.dart';
import 'package:stretchbox/vm/stretched_box_viewmodel.dart';

/// 视图 StretchboxWidget.
/// 提供 状态管理，需要外部传入：正常场景下的Widget以及展开状态下的Widget
// ignore: must_be_immutable
class StretchedBoxWidget extends StatelessWidget {
  StretchedBoxViewModel stretchboxViewModel;
  final Widget normalChild;
  final Widget expandChild;
  Widget Function(BuildContext, StretchedBoxViewModel) bottomBarWidget;

  StretchedBoxWidget({
    Key key,
    this.normalChild,
    this.expandChild,
    this.bottomBarWidget,
    StretchedBoxState stretchState = StretchedBoxState.normal,
  }) : super(key: key) {
    stretchboxViewModel = StretchedBoxViewModel(stretchState);
  }

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

  Widget buildBottomBarWidget(
      BuildContext context, StretchedBoxViewModel stretchboxViewModel) {
    return null;
  }

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
