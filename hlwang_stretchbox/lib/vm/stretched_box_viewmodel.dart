import 'package:flutter/widgets.dart';
import 'package:stretchbox/model/stretched_box_state.dart';

/// ViewModel 当前控件状态
class StretchedBoxViewModel extends ChangeNotifier {
  /// 私有变量，用来记录控件状态
  StretchedBoxState _stretchState;

  /// Constructs 构造
  StretchedBoxViewModel(this._stretchState);

  /// 获取 弹性控件状态
  StretchedBoxState get stretchState => _stretchState;

  /// 设置 弹性控件状态
  set stretchState(StretchedBoxState stretchState) {
    _stretchState = stretchState;
    notifyListeners();
  }

  /// 当前状态，是否展开
  bool isExpand() {
    return stretchState == StretchedBoxState.expand;
  }

  /// 操作方法，如果已展开，会折叠上去，如果折叠中，执行此方法会展开
  void switchStretchMode() {
    print("-----> ${isExpand()}");
    if (isExpand()) {
      switchStretchNormal();
    } else {
      switchStretchExpand();
    }
  }

  /// 对外提供折叠方法入口
  void switchStretchNormal() {
    this.stretchState = StretchedBoxState.normal;
  }

  /// 对外提供展开方法入口
  void switchStretchExpand() {
    this.stretchState = StretchedBoxState.expand;
  }
}
