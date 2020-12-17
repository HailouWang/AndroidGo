import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutterdemo/ui/common/base_page.dart';
import 'package:stretchbox/model/stretched_box_state.dart';
import 'package:stretchbox/view/column_stretched_box.dart';
import 'package:stretchbox/view/custom_stretched_box.dart';
import 'package:stretchbox/vm/stretched_box_viewmodel.dart';

class StretchboxWidgetPageDemo extends StatelessWidget
    with CommonFlutterPageMixIn {
  var title = "";

  StretchboxWidgetPageDemo({this.title});

  List<Widget> normalList = [
    RowTitleSummaryWidget(
      title: "下单时间",
      summary: "2020年10月16日17:24:50",
    ),
    RowTitleSummaryWidget(
      title: "优惠券金额",
      summary: "666",
    ),
    RowTitleSummaryWidget(
      title: "订单状态",
      summary: "卖家已确认",
    ),
  ];

  List<Widget> expandList = [
    RowTitleSummaryWidget(
      title: "商品备注",
      summary: "油泼面，不要油，不要辣",
    ),
    RowTitleSummaryWidget(
      title: "付款金额",
      summary: "666666",
    ),
    RowTitleSummaryWidget(
      title: "物流",
      summary: "小哥快马加鞭送达中....",
    ),
  ];

  @override
  Widget buildContent(BuildContext context) {
    TextStyle titleTextStyle = TextStyle(
      color: Colors.black,
      fontSize: 16,
      fontWeight: FontWeight.bold,
    );
    return Container(
      child: ListView(children: [
        Text("含有底部按钮，默认：折叠，点击自动展开", style: titleTextStyle),
        buildColumnStretchedBoxWidget(),
        SizedBox(
          height: 16,
        ),
        Text("含有底部按钮，默认：展开，点击自动展开", style: titleTextStyle),
        buildColumnStretchedBoxExpandWidget(),
        SizedBox(
          height: 16,
        ),
        Text("自定义底部", style: titleTextStyle),
        buildCustomStretchedBoxWidget(),
        SizedBox(
          height: 66,
        ),
      ]),
    );
  }

  Widget buildColumnStretchedBoxWidget() {
    return ColumnStretchedBoxWidget(
      arrowImageRes: 'images/image_down_expand.png',// 箭头图片资源
      arrowContainerColor: Colors.white,// 箭头图片所在空间背景颜色
//      stretchState: StretchedBoxState.normal,// normal 默认模式
      normalChild: Container(// 总是显示区域
        color: Colors.white,
        child: Column(
          children: normalList,
        ),
      ),
      expandChild: Container(// 展开展示区域
        color: Colors.black12,
        child: Column(
          children: [...normalList, ...expandList],
        ),
      ),
    );
  }

  Widget buildColumnStretchedBoxExpandWidget() {
    ColumnStretchedBoxWidget columnStretchedBoxWidget =
        ColumnStretchedBoxWidget(
      arrowImageRes: 'images/image_down_expand.png',
      arrowContainerColor: Colors.white,
      stretchState: StretchedBoxState.expand,
      normalChild: Container(
        color: Colors.white,
        child: Column(
          children: normalList,
        ),
      ),
      expandChild: Container(
        color: Colors.black12,
        child: Column(
          children: [...normalList, ...expandList],
        ),
      ),
    );

    return columnStretchedBoxWidget;
  }

  Widget buildCustomStretchedBoxWidget() {
    return StretchedBoxWidget(
      normalChild: Container(
        color: Colors.white,
        child: Column(
          children: [
            ...normalList,
          ],
        ),
      ),
      expandChild: Container(
        color: Colors.black12,
        child: Column(
          children: [
            ...normalList,
            ...expandList,
          ],
        ),
      ),
      bottomBarWidget: _buildCustomStretchedBottomWidget,// 自定义底部Widget方式
    );
  }

  Widget _buildCustomStretchedBottomWidget(
      BuildContext context, StretchedBoxViewModel stretchedBoxViewModel) {
    Widget text;
    if (stretchedBoxViewModel?.isExpand() ?? false) {
      text = Text("点击折叠");
    } else {
      text = Text("点击展开");
    }

    return GestureDetector(
      behavior: HitTestBehavior.opaque,
      onTap: () {
        stretchedBoxViewModel?.switchStretchMode();
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
  }

  @override
  Widget actionBarTitleWidget() {
    return Text(title);
  }
}

class RowTitleSummaryWidget extends StatelessWidget {
  final String title;
  final String summary;

  const RowTitleSummaryWidget({
    Key key,
    @required this.title,
    @required this.summary,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(top: 8),
      child: Row(
        children: <Widget>[
          Text(
            title,
            style: const TextStyle(
                fontSize: 12, color: Color.fromRGBO(42, 43, 45, 1)),
          ),
          Text(
            " : ",
            style: const TextStyle(
                fontSize: 12, color: Color.fromRGBO(42, 43, 45, 1)),
          ),
          Expanded(
            child: Text(
              summary ?? '-',
              style: const TextStyle(
                  fontSize: 12, color: Color.fromRGBO(132, 132, 132, 1)),
            ),
          ),
        ],
      ),
    );
  }
}
