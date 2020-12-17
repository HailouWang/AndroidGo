// Copyright (c) 2018, Marco Esposito (marcoesposito1988@gmail.com).
// Please see the AUTHORS file for details. All rights reserved.
// Use of this source code is governed by a BSD-style license
// that can be found in the LICENSE file.

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:stretchbox/view/column_stretched_box.dart';

void main() {
  Widget buildColumnStretchedBoxWidget() {
    return ColumnStretchedBoxWidget(
      arrowImageRes: 'images/image_down_expand.png', // 箭头图片资源
      arrowContainerColor: Colors.white, // 箭头图片所在空间背景颜色
//      stretchState: StretchedBoxState.normal,// normal 默认模式
      normalChild: Container(
        // 总是显示区域
        color: Colors.white,
        child: Column(
          children: [
            Text("Normal Area 1"),
            Text("Normal Area 2"),
            Text("Normal Area 3"),
          ],
        ),
      ),
      expandChild: Container(
        // 展开展示区域
        color: Colors.black12,
        child: Column(
          children: [
            Text("Collapse Area 1"),
            Text("Collapse Area 2"),
          ],
        ),
      ),
    );
  }
}
