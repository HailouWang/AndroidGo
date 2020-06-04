
## 一、StateMachine 要解决的问题

从设计模式的角度来讲，状态模式和策略模式类图是一样的，所以工作原理也类似。但相比较于策略模式，状态模式需要管理好状态树，以及维护状态对象的生命周期。

所以，将 Android 源代码中 [``WifiStateMachine``](http://androidxref.com/8.0.0_r4/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java) 所依赖使用的状态机框架拿过来，用来生成状态树以及管理状态生命周期。


> ### 案例代码，已上传 [``AndroidGo``](https://github.com/HailouWang/AndroidGo)

## 二、使用方式

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    ...
    implementation 'com.github.hailouwang:statemachine:1.0.0'
}
```

## 三、Demo 案例

### 1、``单状态`` 案例

```java
public static void main(String args[]){
    testHelloWorld();
}

static class HelloWorld extends StateMachine {
    HelloWorld(String name) {
        super(name);
        addState(mState1);
        setInitialState(mState1);
    }

    public static HelloWorld makeHelloWorld() {
        HelloWorld hw = new HelloWorld("hlwang");
        hw.start();
        return hw;
    }

    class State1 extends State {
        @Override
        public boolean processMessage(Message msg) {
            log("Hello World");
            return HANDLED;
        }
    }

    State1 mState1 = new State1();
}

void testHelloWorld() {
    HelloWorld hw = HelloWorld.makeHelloWorld();
    hw.sendMessage(hw.obtainMessage());
}
```

### 2、``多状态协同`` 案例

构建如下状态树：

<pre>
        mP1      mP2
       /   \
      mS2   mS1
</pre>

```java
public static void main(String args[]){
    testMoreStateTransaction();
}

static class Hsm1 extends StateMachine {
    public static final int CMD_1 = 1;
    public static final int CMD_2 = 2;
    public static final int CMD_3 = 3;
    public static final int CMD_4 = 4;
    public static final int CMD_5 = 5;

    public static Hsm1 makeHsm1() {
        Log.d("hlwang", "makeHsm1 E");
        Hsm1 sm = new Hsm1("hlwang");
        sm.start();
        Log.d("hlwang", "makeHsm1 X");
        return sm;
    }

    Hsm1(String name) {
        super(name);
        log("ctor E");

        // Add states, use indentation to show hierarchy
        addState(mP1);
        addState(mS1, mP1);
        addState(mS2, mP1);
        addState(mP2);

        // Set the initial state
        setInitialState(mS1);
        log("ctor X");
    }

    class P1 extends State {
        @Override
        public void enter() {
            log("mP1.enter");
        }

        @Override
        public boolean processMessage(Message message) {
            boolean retVal;
            log("mP1.processMessage what=" + message.what);
            switch (message.what) {
                case CMD_2:
                    // CMD_2 will arrive in mS2 before CMD_3
                    sendMessage(obtainMessage(CMD_3));
                    deferMessage(message);
                    transitionTo(mS2);
                    retVal = HANDLED;
                    break;
                default:
                    // Any message we don't understand in this state invokes unhandledMessage
                    retVal = NOT_HANDLED;
                    break;
            }
            return retVal;
        }

        @Override
        public void exit() {
            log("mP1.exit");
        }
    }

    class S1 extends State {
        @Override
        public void enter() {
            log("mS1.enter");
        }

        @Override
        public boolean processMessage(Message message) {
            log("S1.processMessage what=" + message.what);
            if (message.what == CMD_1) {
                // Transition to ourself to show that enter/exit is called
                transitionTo(mS1);
                return HANDLED;
            } else {
                // Let parent process all other messages
                return NOT_HANDLED;
            }
        }

        @Override
        public void exit() {
            log("mS1.exit");
        }
    }

    class S2 extends State {
        @Override
        public void enter() {
            log("mS2.enter");
        }

        @Override
        public boolean processMessage(Message message) {
            boolean retVal;
            log("mS2.processMessage what=" + message.what);
            switch (message.what) {
                case (CMD_2):
                    sendMessage(obtainMessage(CMD_4));
                    retVal = HANDLED;
                    break;
                case (CMD_3):
                    deferMessage(message);
                    transitionTo(mP2);
                    retVal = HANDLED;
                    break;
                default:
                    retVal = NOT_HANDLED;
                    break;
            }
            return retVal;
        }

        @Override
        public void exit() {
            log("mS2.exit");
        }
    }

    class P2 extends State {

        @Override
        public void enter() {
            log("mP2.enter");
            sendMessage(obtainMessage(CMD_5));
        }

        @Override
        public boolean processMessage(Message message) {
            log("P2.processMessage what=" + message.what);
            switch (message.what) {
                case (CMD_3):
                    break;
                case (CMD_4):
                    break;
                case (CMD_5):
                    transitionToHaltingState();
                    break;
            }
            return HANDLED;
        }

        @Override
        public void exit() {
            log("mP2.exit");
        }
    }

    @Override
    public void onHalting() {
        log("halting");
        synchronized (this) {
            this.notifyAll();
        }
    }

    P1 mP1 = new P1();
    S1 mS1 = new S1();
    S2 mS2 = new S2();
    P2 mP2 = new P2();
}
```

## 四、备注

1、处于状态子树的状态，如果未处理请求，会将请求交给父状态继续处理。如上案例所示，S1 未处理的请求，P1 会接手处理。