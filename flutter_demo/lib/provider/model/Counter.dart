
import 'package:flutter/widgets.dart';

class Counter with ChangeNotifier {//1
  int _count;
  Counter(this._count);

  void add() {
    _count++;
    notifyListeners();//2
  }
  get count => _count;//3
}