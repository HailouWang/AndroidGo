import 'package:collapsebox/model/collapsebox_state.dart';
import 'package:flutter/widgets.dart';

/// ViewModel 当前控件状态
class CollapseboxBoxViewModel extends ChangeNotifier {
  /// 私有变量，用来记录控件状态
  CollapseBoxState _collapseboxState;

  /// Constructs 构造
  CollapseboxBoxViewModel(this._collapseboxState);

  /// 获取 弹性控件状态
  CollapseBoxState get collapseboxState => _collapseboxState;

  /// 设置 弹性控件状态
  set collapseboxState(CollapseBoxState stretchState) {
    _collapseboxState = stretchState;
    notifyListeners();
  }

  /// 当前状态，是否展开
  bool isExpand() {
    return collapseboxState == CollapseBoxState.expand;
  }

  /// 当前状态，是否折叠
  bool isNormalOrCollapse() {
    return !isExpand();
  }

  /// 操作方法，如果已展开，会折叠上去，如果折叠中，执行此方法会展开
  void switchCollapseBoxMode() {
    print("-----> ${isExpand()}");
    if (isExpand()) {
      switchCollapseBoxNormal();
    } else {
      switchCollapseBoxExpand();
    }
  }

  /// 对外提供折叠方法入口
  void switchCollapseBoxNormal() {
    this.collapseboxState = CollapseBoxState.normal;
  }

  /// 对外提供展开方法入口
  void switchCollapseBoxExpand() {
    this.collapseboxState = CollapseBoxState.expand;
  }
}
