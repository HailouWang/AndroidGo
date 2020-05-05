import 'dart:convert';
import 'dart:isolate';

import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter/services.dart';
import 'package:flutterdemo/provider/ProviderDemo.dart';
import 'package:http/http.dart' as http;

// => 单行函数或者方法的简写
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
//    final wordPair = new WordPair.random();
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
          // This is the theme of your application.
          //
          // Try running your application with "flutter run". You'll see the
          // application has a blue toolbar. Then, without quitting the app, try
          // changing the primarySwatch below to Colors.green and then invoke
          // "hot reload" (press "r" in the console where you ran "flutter run",
          // or simply save your changes to "hot reload" in a Flutter IDE).
          // Notice that the counter didn't reset back to zero; the application
          // is not restarted.
          primarySwatch: Colors.blue,
          primaryColor: Colors.white),
      home: new RandomWords(),
      routes: <String, WidgetBuilder>{
        '/a': (BuildContext context) => new MyPage14(title: 'Page A'),
        '/b': (BuildContext context) => new MyPage14(title: 'Page B'),
        '/c': (BuildContext context) => new MyPage14(title: 'Page C'),
        '/d': (BuildContext context) => new CustomButtonWidget(),
        '/provider': (BuildContext context) => new ProviderDemo(),
      },
    );
  }
}

class RandomWords extends StatelessWidget {
  final _ListDatas = <String>[];

  final _biggerFont = const TextStyle(fontSize: 18.0);

  @override
  Widget build(BuildContext context) {
//    final wordPair = new WordPair.random();
//    return new Text(wordPair.asPascalCase);
    return new Scaffold(
      appBar: AppBar(
        title: new Text('Startup Name Generator'),
        actions: <Widget>[],
      ),
      body: _buildListDatas(context),
    );
  }

  Widget _buildListDatas(BuildContext context) {
    return new ListView.builder(
      padding: const EdgeInsets.all(16.0),
      itemBuilder: (context, i) {
        if (i.isOdd) return new Divider();

        final index = i ~/ 2;

        if (index >= _ListDatas.length) {
          return null;
        }

        return _buildRow(context, _ListDatas[index], index);
      },
    );
  }

  Widget _buildRow(BuildContext context, String content, int index) {
    return new ListTile(
      title: new Text(
        content,
        style: _biggerFont,
      ),
      onTap: () {
        _onClick(context, index);
      },
    );
  }

  RandomWords() {
    _ListDatas.add("01 Hello World!!!");
    _ListDatas.add("02 布局");
    _ListDatas.add("03 使用 Material 组件");
    _ListDatas.add("04 手势");
    _ListDatas.add("05 根据用户输入改变widget");
    _ListDatas.add("06 数据搜集 & 数据展示 & 状态保存分离");
    _ListDatas.add("07 综合案例：购物车");
    _ListDatas.add("08 与 Android 相比：StatelessWidget");
    _ListDatas.add("09 与 Android 相比：StatefullWidget");
    _ListDatas.add("10 与 Android 相比：添加或者删除组件");
    _ListDatas.add("11 与 Android 相比：View.animate");
    _ListDatas.add("12 使用Canvas draw/paint");
    _ListDatas.add("13 自定义Widgets");
    _ListDatas.add("14 Intents --> Navigator切换到命名的路由");
    _ListDatas.add("15 Intents --> 处理来自外部应用程序传入的Intents");
    _ListDatas.add("16 异步请求 runOnUiThread");
    _ListDatas.add("17 Isolate 异步请求 runOnUiThread");
    _ListDatas.add("18 在Flutter中显示进度指示器");
    _ListDatas.add("19 在Flutter中监听生命周期");
    _ListDatas.add("20 Google Provider Demo");
  }

  void _onClick(BuildContext context, int index) {
    index += 1;

    if (index == 1) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return Center(
            child: new Text("Hello,World", textDirection: TextDirection.ltr));
      }));
    } else if (index == 2) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
            title: '布局', // used by the OS task switcher
            home: new MyScaffold());
      }));
    } else if (index == 3) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'Flutter Tutorial',
          home: new TutorialHome(),
        );
      }));
    } else if (index == 4) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '手势',
          home: new MyButton(),
        );
      }));
    } else if (index == 5) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'StateFullWidget',
          home: new Counter(),
        );
      }));
    } else if (index == 6) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'StateFullWidget',
          home: new Counter06(),
        );
      }));
    } else if (index == 7) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'StateFullWidget',
          home: new ShoppingList(
            products: <Product>[
              new Product(name: 'Eggs'),
              new Product(name: 'Flour'),
              new Product(name: 'Chocolate chips'),
            ],
          ),
        );
      }));
    } else if (index == 8) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'StateFullWidget',
          home: new Text(
            '08 与 Android 相比：StatelessWidget',
            style: new TextStyle(fontWeight: FontWeight.bold),
          ),
        );
      }));
    } else if (index == 9) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: 'StateFullWidget',
          home: new SampleAppPage(),
        );
      }));
    } else if (index == 10) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '10 添加/删除组件',
          home: new SampleAppPage10(),
        );
      }));
    } else if (index == 11) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '11 与 Android 相比：View.animate',
          home: new SampleAppPage11(),
        );
      }));
    } else if (index == 12) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '12 与 Android 相比：View.animate',
          home: new Signature(),
        );
      }));
    } else if (index == 13) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '13 自定义Widgets',
          home: new CustomButtonWidget(),
        );
      }));
    } else if (index == 14) {
      if (count14 % 4 == 0) {
        Navigator.of(context).pushNamed('/a',arguments: "hi");
      } else if (count14 % 4 == 1) {
        Navigator.of(context).pushNamed('/b');
      } else if (count14 % 4 == 2) {
        Navigator.of(context).pushNamed('/c');
      } else if (count14 % 4 == 3) {
        Navigator.of(context).pushNamed('/d');
      }

      count14++;
    } else if (index == 15) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new IntentsFromOuterClassPage();
      }));
    } else if (index == 16) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '16 ---> 异步请求 runOnUiThread',
          home: new ListAsyncPage(),
        );
      }));
    } else if (index == 17) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '17 ---> Isolate 异步请求 runOnUiThread',
          home: new ListAsyncPageIsolate(),
        );
      }));
    }else if (index == 18) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '18 在Flutter中显示进度指示器',
          home: new ProgressIndicator(),
        );
      }));
    }else if (index == 19) {
      Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
        return new MaterialApp(
          title: '19 在Flutter中监听生命周期',
          home: new LifecycleWatcher(),
        );
      }));
    }else if (index == 20) {
      Navigator.of(context).pushNamed('/provider');
    }
  }
}

// 19、在Flutter中监听生命周期

class LifecycleWatcher extends StatefulWidget {
  @override
  _LifecycleWatcherState createState() => new _LifecycleWatcherState();
}

class _LifecycleWatcherState extends State<LifecycleWatcher> with WidgetsBindingObserver {
  AppLifecycleState _lastLifecyleState;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    setState(() {
      _lastLifecyleState = state;
    });
  }

  @override
  Widget build(BuildContext context) {
    if (_lastLifecyleState == null)
      return new Text('This widget has not observed any lifecycle changes.', textDirection: TextDirection.ltr);
    return new Text('The most recent lifecycle state this widget observed was: $_lastLifecyleState.',
        textDirection: TextDirection.ltr);
  }
}

// 18、在Flutter中显示进度指示器

class ProgressIndicator extends StatefulWidget {
  ProgressIndicator({Key key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return new _SampleAppPageState18();
  }
}

class _SampleAppPageState18 extends State<ProgressIndicator> {
  List widgets = [];

  @override
  void initState() {
    super.initState();
    loadData();
  }

  showLoadingDialog() {
    if (widgets.length == 0) {
      return true;
    }

    return false;
  }

  getBody() {
    if (showLoadingDialog()) {
      return getProgressDialog();
    } else {
      return getListView();
    }
  }

  getProgressDialog() {
    return new Center(child: new CircularProgressIndicator());
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: new AppBar(
          title: new Text("Sample App"),
        ),
        body: getBody());
  }

  ListView getListView() => new ListView.builder(
      itemCount: widgets.length,
      itemBuilder: (BuildContext context, int position) {
        return getRow(position);
      });

  Widget getRow(int i) {
    return new Padding(padding: new EdgeInsets.all(10.0), child: new Text("Row ${widgets[i]["title"]}"));
  }

  loadData() async {
    String dataURL = "https://jsonplaceholder.typicode.com/posts";
    http.Response response = await http.get(dataURL);
    setState(() {
      widgets = json.decode(response.body);
    });
  }
}

// 17、异步请求
class ListAsyncPageIsolate extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _ListAsyncPageState(17);
  }
}

// 16、异步请求
class ListAsyncPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _ListAsyncPageState(16);
  }
}

class _ListAsyncPageState extends State<StatefulWidget> {
  List widgets = [];

  var index = 16;

  _ListAsyncPageState(this.index);

  @override
  void initState() {
    super.initState();

    if (index == 17) {
      loadDataIsolate();
    } else {
      loadData();
    }
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: new AppBar(
          title: new Text("16 ===> 异步请求 runOnUiThread"),
        ),
        body: new ListView.builder(
            itemCount: widgets.length,
            itemBuilder: (BuildContext context, int position) {
              return getRow(position);
            }));
  }

  Widget getRow(int i) {
    return new Padding(
        padding: new EdgeInsets.all(10.0),
        child: new Text("Row ${widgets[i]["title"]}"));
  }

  // 要异步运行代码，可以将函数声明为异步函数，并在该函数中等待这个耗时任务
  loadData() async {
    String dataURL = "https://jsonplaceholder.typicode.com/posts";
    http.Response response = await http.get(dataURL);
    setState(() {
      widgets = json.decode(response.body);
    });
  }

  // ============================================================start
  loadDataIsolate() async {
    ReceivePort receivePort = new ReceivePort();
    await Isolate.spawn(dataLoader, receivePort.sendPort);

    // The 'echo' isolate sends it's SendPort as the first message
    SendPort sendPort = await receivePort.first;

    List msg = await sendReceive(
        sendPort, "https://jsonplaceholder.typicode.com/posts");

    setState(() {
      widgets = msg;
    });
  }

  // the entry point for the isolate
  static dataLoader(SendPort sendPort) async {
    // Open the ReceivePort for incoming messages.
    ReceivePort port = new ReceivePort();

    // Notify any other isolates what port this isolate listens to.
    sendPort.send(port.sendPort);

    await for (var msg in port) {
      String data = msg[0];
      SendPort replyTo = msg[1];

      String dataURL = data;
      http.Response response = await http.get(dataURL);
      // Lots of JSON to parse
      replyTo.send(json.decode(response.body));
    }
  }

  Future sendReceive(SendPort port, msg) {
    ReceivePort response = new ReceivePort();
    port.send([msg, response.sendPort]);
    return response.first;
  }
// ============================================================end
}

// 15、Intents
class IntentsFromOuterClassPage extends StatefulWidget {
  @override
  State<IntentsFromOuterClassPage> createState() {
    return new IntentsFromOuterClassPageState();
  }
}

class IntentsFromOuterClassPageState extends State<IntentsFromOuterClassPage> {
  static const platform = const MethodChannel('app.channel.shared.data');
  String dataShared = "No data";

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('15 Intents --> 处理来自外部应用程序传入的Intents'),
      ),
      body: new Center(
        child: new Text(dataShared),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
  }

  getSharedText() async {
    var sharedData = await platform.invokeMethod("getSharedText");
    if (sharedData != null) {
      setState(() {
        dataShared = sharedData;
      });
    }
  }
}

// 14、Intents
var count14 = 0;

class MyPage14 extends StatelessWidget {
  String label;

  MyPage14({String title}) {
    this.label = title;
  }

  @override
  Widget build(BuildContext context) {

    String obj = ModalRoute.of(context).settings.arguments;
    if(obj != null){
      print("参数---> "+obj);
    }

    return new Scaffold(
      appBar: new AppBar(
        title: new Text('13 自定义Widgets'),
      ),
      body: new Center(
        child: new Text(label),
      ),
    );
  }
}

// 13 自定义Widgets

class CustomButton extends StatelessWidget {
  final String label;

  CustomButton(this.label);

  @override
  Widget build(BuildContext context) {
    return new RaisedButton(
      onPressed: () {
        Navigator.of(context).pushNamed('/a');
      },
      child: new Text(label),
    );
  }
}

class CustomButtonWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("09 与 Android 相比：StatefullWidget"),
      ),
      body: new Center(child: new CustomButton("Hello")),
    );
  }
}

// 12 使用Canvas draw/paint CustomPaint和CustomPainter
// .. 运算符
class SignaturePainter12 extends CustomPainter {
  final List<Offset> points;

  SignaturePainter12(this.points);

  @override
  void paint(Canvas canvas, Size size) {
    var paint = new Paint()
      ..strokeCap = StrokeCap.round
      ..strokeWidth = 5.0
      ..color = Colors.black;

    paint.color = Colors.red;

    for (int i = 0; i < points.length; i++) {
      if (points[i] != null && points[i + 1] != null) {
        canvas.drawLine(points[i], points[i + 1], paint);
      }
    }
  }

  @override
  bool shouldRepaint(SignaturePainter12 oldDelegate) {
    return oldDelegate.points != this.points;
  }
}

class Signature extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new SignatureState();
  }
}

class SignatureState extends State<Signature> {
  List<Offset> _poinits = <Offset>[];

  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onPanUpdate: (DragUpdateDetails details) {
        setState(() {
          RenderBox referenceBox = context.findRenderObject();
          Offset localPosition =
              referenceBox.globalToLocal(details.globalPosition);
          _poinits = new List.from(_poinits)..add(localPosition);
        });
      },
      onPanEnd: (DragEndDetails details) {
        _poinits.add(null);
      },
      child: new CustomPaint(painter: new SignaturePainter12(_poinits)),
    );
//    return new Scaffold(
//        appBar: new AppBar(
//          title: new Text('11 与 Android 相比：View.animate'),
//        ),
//        body: new GestureDetector(
//          onPanUpdate: (DragUpdateDetails details) {
//            setState(() {
//              RenderBox referenceBox = context.findRenderObject();
//              Offset localPosition =
//              referenceBox.globalToLocal(details.globalPosition);
//              _poinits = new List.from(_poinits)..add(localPosition);
//            });
//          },
//          onPanEnd: (DragEndDetails details) {
//            _poinits.add(null);
//          },
//          child: new CustomPaint(painter: new SignaturePainter12(_poinits)),
//        ),
//    );
  }
}

// 11 与 Android 相比：View.animate
class SampleAppPage11 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _SampleAppPageState11();
  }
}

class _SampleAppPageState11 extends State<SampleAppPage11>
    with TickerProviderStateMixin {
  AnimationController controller;
  CurvedAnimation curve;

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('11 与 Android 相比：View.animate'),
      ),
      body: new Center(
        child: new Container(
          child: new FadeTransition(
            opacity: curve,
            child: new FlutterLogo(
              size: 100.0,
            ),
          ),
        ),
      ),
      floatingActionButton: new FloatingActionButton(
          tooltip: "Fade",
          child: new Icon(Icons.brush),
          onPressed: () {
            controller.forward();
          }),
    );
  }

  @override
  void initState() {
    controller = new AnimationController(
        duration: const Duration(milliseconds: 2000), vsync: this);
    curve = new CurvedAnimation(parent: controller, curve: Curves.easeIn);
  }
}

// 10 添加/删除组件
class SampleAppPage10 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _SampleAppPageState10();
  }
}

class _SampleAppPageState10 extends State<SampleAppPage10> {
  // Deafult value for toggle
  bool toogle = true;

  void _toggle() {
    setState(() {
      toogle = !toogle;
    });
  }

  _getToggleChild() {
    if (toogle) {
      return new Text('Toggle One');
    } else {
      return new MaterialButton(
        onPressed: () {},
        child: new Text('Toggle Two'),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("10 添加/删除组件"),
      ),
      body: new Center(
        child: _getToggleChild(),
      ),
      floatingActionButton: new FloatingActionButton(
        onPressed: _toggle,
        tooltip: 'Update Text',
        child: new Icon(Icons.update),
      ),
    );
  }
}

// 09 状态变化

class SampleAppPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _SampleAppPageState();
  }
}

class _SampleAppPageState extends State<SampleAppPage> {
  String textToShow = "I like flutter";

  void _updateText() {
    setState(() {
      textToShow = "Flutter is Awesome!";
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("09 与 Android 相比：StatefullWidget"),
      ),
      body: new Center(
        child: new Text(textToShow),
      ),
      floatingActionButton: new FloatingActionButton(
        onPressed: _updateText,
        tooltip: 'Update Text',
        child: new Icon(Icons.update),
      ),
    );
  }
}

// 07 假设一个购物应用程序，该应用程序显示出售的各种产品，并维护一个购物车
class ShoppingList extends StatefulWidget {
  ShoppingList({Key key, this.products}) : super(key: key);

  final List<Product> products;

  // The framework calls createState the first time a widget appears at a given
  // location in the tree. If the parent rebuilds and uses the same type of
  // widget (with the same key), the framework will re-use the State object
  // instead of creating a new State object.

  @override
  _ShoppingListState createState() => new _ShoppingListState();
}

class _ShoppingListState extends State<ShoppingList> {
  Set<Product> _shoppingCart = new Set<Product>();

  /**
   * 以完成仅需执行一次的工作
   */
  @override
  void initState() {}

  /**
   * 释放、清理工作
   */
  @override
  void dispose() {}

  void _handleCartChanged(Product product, bool inCart) {
    // 为了通知框架它改变了它的内部状态，需要调用setState。调用setState将该widget标记为”dirty”(脏的)，并且计划在下次应用程序需要更新屏幕时重新构建它。
    // 如果在修改widget的内部状态后忘记调用setState，框架将不知道您的widget是”dirty”(脏的)，并且可能不会调用widget的build方法，这意味着用户界面可能不会更新以展示新的状态。

    setState(() {
      // When user changes what is in the cart, we need to change _shoppingCart
      // inside a setState call to trigger a rebuild. The framework then calls
      // build, below, which updates the visual appearance of the app.

      if (inCart)
        _shoppingCart.add(product);
      else
        _shoppingCart.remove(product);
    });
  }

  /**
   * StatefulWidget 新、旧状态改变时，可以将新、旧两个状态diff
   */
  @override
  void didUpdateWidget(ShoppingList oldWidget) {}

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('Shopping List'),
      ),
      body: new ListView(
        padding: new EdgeInsets.symmetric(vertical: 8.0),
        children: widget.products.map((Product product) {
          return new ShoppingListItem(
            product: product,
            inCart: _shoppingCart.contains(product),
            onCartChanged: _handleCartChanged,
          );
        }).toList(),
      ),
    );
  }
}

class Product {
  const Product({this.name});

  final String name;
}

// 定义 callback
typedef void CartChangedCallback(Product product, bool inCart);

/**
 * 该ShoppingListItem widget是无状态的。它将其在构造函​​数中接收到的值存储在final成员变量中，然后在build函数中使用它们。
 */
class ShoppingListItem extends StatelessWidget {
  ShoppingListItem({Product product, this.inCart, this.onCartChanged})
      : product = product,
        super(key: new ObjectKey(product));

  final Product product;
  final bool inCart;
  final CartChangedCallback onCartChanged;

  Color _getColor(BuildContext context) {
    // The theme depends on the BuildContext because different parts of the tree
    // can have different themes.  The BuildContext indicates where the build is
    // taking place and therefore which theme to use.

    return inCart ? Colors.black54 : Theme.of(context).primaryColor;
  }

  TextStyle _getTextStyle(BuildContext context) {
    if (!inCart) return null;

    return new TextStyle(
      color: Colors.black54,
      decoration: TextDecoration.lineThrough,
    );
  }

  @override
  Widget build(BuildContext context) {
    return new ListTile(
      onTap: () {
        onCartChanged(product, !inCart);
      },
      leading: new CircleAvatar(
        backgroundColor: _getColor(context),
        child: new Text(product.name[0]),
      ),
      title: new Text(product.name, style: _getTextStyle(context)),
    );
  }
}

// 06
class CounterDisplay extends StatelessWidget {
  CounterDisplay({this.count});

  final int count;

  @override
  Widget build(BuildContext context) {
    return new Text('Count: $count');
  }
}

class CounterIncrementor extends StatelessWidget {
  CounterIncrementor({this.onPressed});

  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return new RaisedButton(
      onPressed: onPressed,
      child: new Text('Increment'),
    );
  }
}

class Counter06 extends StatefulWidget {
  _CounterState06 createState() => new _CounterState06();
}

class _CounterState06 extends State<Counter06> {
  int _counter = 0;

  void _increment() {
    setState(() {
      ++_counter;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Row(children: <Widget>[
      new CounterIncrementor(onPressed: _increment),
      new CounterDisplay(count: _counter),
    ]);
  }
}

// 05 根据用户输入改变widget
class Counter extends StatefulWidget {
  // This class is the configuration for the state. It holds the
  // values (in this nothing) provided by the parent and used by the build
  // method of the State. Fields in a Widget subclass are always marked "final".

  @override
  _CounterState createState() => new _CounterState();
}

class _CounterState extends State<Counter> {
  int _counter = 0;

  void _increment() {
    setState(() {
      // This call to setState tells the Flutter framework that
      // something has changed in this State, which causes it to rerun
      // the build method below so that the display can reflect the
      // updated values. If we changed _counter without calling
      // setState(), then the build method would not be called again,
      // and so nothing would appear to happen.
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance
    // as done by the _increment method above.
    // The Flutter framework has been optimized to make rerunning
    // build methods fast, so that you can just rebuild anything that
    // needs updating rather than having to individually change
    // instances of widgets.
    return new Row(
      children: <Widget>[
        new RaisedButton(
          onPressed: _increment,
          child: new Text('Increment'),
        ),
        new Text('Count: $_counter'),
      ],
    );
  }
}

// 04 处理手势
class MyButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: () {
        print('MyButton was tapped!');
      },
      onDoubleTap: () {
        print('MyButton was 双击!');
      },
      child: new Container(
        height: 36.0,
        padding: const EdgeInsets.all(8.0),
        margin: const EdgeInsets.symmetric(horizontal: 8.0),
        decoration: new BoxDecoration(
          borderRadius: new BorderRadius.circular(5.0),
          color: Colors.lightGreen[500],
        ),
        child: new Center(
          child: new Text('Engage'),
        ),
      ),
    );
  }
}

// 03 Material 组件
class TutorialHome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //Scaffold是Material中主要的布局组件.
    return new Scaffold(
      appBar: new AppBar(
        leading: new IconButton(
          icon: new Icon(Icons.menu),
          tooltip: 'Navigation menu',
          onPressed: null,
        ),
        title: new Text('Example title'),
        actions: <Widget>[
          new IconButton(
            icon: new Icon(Icons.search),
            tooltip: 'Search',
            onPressed: null,
          ),
        ],
      ),
      //body占屏幕的大部分
      body: new Center(
        child: new Text('Hello, world!'),
      ),
      floatingActionButton: new FloatingActionButton(
        tooltip: 'Add', // used by assistive technologies
        child: new Icon(Icons.add),
        onPressed: null,
      ),
    );
  }
}

// 02 布局
class MyAppBar extends StatelessWidget {
  MyAppBar({this.title});

  // Widget子类中的字段往往都会定义为"final"

  final Widget title;

  @override
  Widget build(BuildContext context) {
    return new Container(
      height: 256.0, // 单位是逻辑上的像素（并非真实的像素，类似于浏览器中的像素）
      padding: const EdgeInsets.symmetric(horizontal: 8.0),
      decoration: new BoxDecoration(color: Colors.blue[500]),
      // Row 是水平方向的线性布局（linear layout）
      child: new Row(
        //列表项的类型是 <Widget>
        children: <Widget>[
          new IconButton(
            icon: new Icon(Icons.menu),
            tooltip: 'Navigation menu',
            onPressed: null, // null 会禁用 button
          ),
          // Expanded expands its child to fill the available space.
          new Expanded(
            child: title,
          ),
          new IconButton(
            icon: new Icon(Icons.search),
            tooltip: 'Search',
            onPressed: null,
          ),
        ],
      ),
    );
  }
}

class MyScaffold extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Material 是UI呈现的“一张纸”
    return new Material(
      // Column is 垂直方向的线性布局.
      child: new Column(
        children: <Widget>[
          new MyAppBar(
            title: new Text(
              'Example title',
              style: Theme.of(context).primaryTextTheme.title,
            ),
          ),
          new Expanded(
            child: new Center(
              child: new Text('Hello, world!'),
            ),
          ),
        ],
      ),
    );
  }
}
