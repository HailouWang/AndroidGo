import 'package:flutter/widgets.dart';
import 'package:stretchbox/model/stretched_box_state.dart';

/// ViewModel 当前控件状态
class StretchedBoxViewModel extends ChangeNotifier {
  StretchedBoxState _stretchState;

  StretchedBoxViewModel(this._stretchState);

  StretchedBoxState get stretchState => _stretchState;

  set stretchState(StretchedBoxState stretchState) {
    _stretchState = stretchState;
    notifyListeners();
  }

  bool isExpand() {
    return stretchState == StretchedBoxState.expand;
  }

  void switchStretchMode() {
    print("-----> ${isExpand()}");
    if (isExpand()) {
      switchStretchNormal();
    } else {
      switchStretchExpand();
    }
  }

  void switchStretchNormal() {
    this.stretchState = StretchedBoxState.normal;
  }

  void switchStretchExpand() {
    this.stretchState = StretchedBoxState.expand;
  }
}
