// Copyright (c) 2018, Marco Esposito (marcoesposito1988@gmail.com).
// Please see the AUTHORS file for details. All rights reserved.
// Use of this source code is governed by a BSD-style license
// that can be found in the LICENSE file.

import 'package:collapsebox/view/column_collapsebox.dart';
import 'package:collapsebox/view/custom_collapsebox.dart';
import 'package:collapsebox/vm/collapsebox_viewmodel.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

void main() {
  Widget buildColumnCollapseBoxWidget() {
    return ColumnCollapseBoxWidget(
      // arrow image resource
      bottomArrowImageRes: 'images/image_down_expand.png',
      // arrow image area bg color
      bottomBarColor: Colors.white,
//      stretchState: StretchedBoxState.normal,// normal default
      // area show always
      alwaysShowChild: Container(
        color: Colors.white,
        child: Column(
          children: [
            Text("Normal Area 1"),
            Text("Normal Area 2"),
            Text("Normal Area 3"),
          ],
        ),
      ),
      // area show by expand
      collapsedChild: Container(
        // 展开展示区域
        color: Colors.black12,
        child: Column(
          children: [
            Text("Collapse Area 1"),
            Text("Collapse Area 2"),
          ],
        ),
      ),
//      bottomBarWidget: _buildCustomBottomWidget,// bottom widget custom set
    );
  }

  Widget buildCustomStretchedBoxWidget() {
    return CollapseBoxWidget(
      alwaysShowChild: Container(
        color: Colors.white,
        child: Column(
          children: [
            Text("Normal Area 1"),
            Text("Normal Area 2"),
            Text("Normal Area 3"),
          ],
        ),
      ),
      collapsedChild: Container(
        color: Colors.black12,
        child: Column(
          children: [
            Text("Collapse Area 1"),
            Text("Collapse Area 2"),
          ],
        ),
      ),
      // custom bottomBar Widget set
      bottomBarWidget:
          (BuildContext context, CollapseboxBoxViewModel collapseboxViewModel) {
        Widget text;
        if (collapseboxViewModel?.isExpand() ?? false) {
          text = Text("Click Collapse");
        } else {
          text = Text("Click Expand");
        }

        return GestureDetector(
          behavior: HitTestBehavior.opaque,
          onTap: () {
            collapseboxViewModel?.switchCollapseBoxMode();
          },
          child: Container(
            height: 32,
            width: double.infinity,
            color: Colors.deepPurpleAccent,
            child: Center(
              child: text,
            ),
          ),
        );
      }, // 自定义底部Widget方式
    );
  }
}
