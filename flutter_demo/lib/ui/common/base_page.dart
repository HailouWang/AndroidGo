import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

mixin CommonFlutterPageMixIn on Widget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        width: double.infinity,
        height: double.infinity,
        child: Column(
          children: <Widget>[
            AppBar(title: actionBarTitleWidget()),
            Expanded(
              child: Container(
                color: Theme.of(context).scaffoldBackgroundColor,
                child: Stack(
                  children: <Widget>[
                    buildContent(context),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildContent(BuildContext context);

  Widget actionBarTitleWidget();
}
