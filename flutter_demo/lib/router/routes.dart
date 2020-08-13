import 'package:fluro/fluro.dart';
import 'package:flutterdemo/router/route_handlers.dart';

class Routers {
  static String special_list = "special_list";

  static void configureRoutes(Router router) {
    router.define(special_list, handler: special_list_handler);
  }
}
