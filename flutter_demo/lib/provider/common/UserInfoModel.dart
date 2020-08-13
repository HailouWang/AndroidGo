import 'package:flutter/material.dart';

class UserInfoModel extends ChangeNotifier {
  bool isLogin;

  void login() {
    isLogin = true;
  }

  void logout() {
    isLogin = false;
    notifyListeners();
  }

  bool isUserLogin() {
    return isLogin;
  }
}
