package com.github.hailouwang.demosforapi.kotlin

class InnerObjectTest {
    // 相当于 public 静态内部类
    object Test{
        val a = "6666";
    }
}

//public class InnerObjectTest {
//    static class Test{
//        private static String a = "666";
//
//        public static String getA(){
//            return a;
//        }
//    }
//}

/**
public final class com/github/hailouwang/demosforapi/kotlin/InnerObjectTest {


// access flags 0x1
public <init>()V
L0
LINENUMBER 3 L0
ALOAD 0
INVOKESPECIAL java/lang/Object.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

@Lkotlin/Metadata;(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2={"Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest;", "", "()V", "Test", "app_debug"})
// access flags 0x19
public final static INNERCLASS com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test com/github/hailouwang/demosforapi/kotlin/InnerObjectTest Test
// compiled from: InnerObjectTest.kt
}


// ================com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test.class =================
// class version 50.0 (50)
// access flags 0x31
public final class com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test {


// access flags 0x1A
private final static Ljava/lang/String; a = "6666"
@Lorg/jetbrains/annotations/NotNull;() // invisible

// access flags 0x11
public final getA()Ljava/lang/String;
@Lorg/jetbrains/annotations/NotNull;() // invisible
L0
LINENUMBER 6 L0
GETSTATIC com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test.a : Ljava/lang/String;
ARETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x2
private <init>()V
L0
LINENUMBER 5 L0
ALOAD 0
INVOKESPECIAL java/lang/Object.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x19
public final static Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test; INSTANCE

// access flags 0x8
static <clinit>()V
L0
LINENUMBER 5 L0
NEW com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test
DUP
INVOKESPECIAL com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test.<init> ()V
ASTORE 0
ALOAD 0
PUTSTATIC com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test.INSTANCE : Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test;
L1
LINENUMBER 6 L1
LDC "6666"
PUTSTATIC com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test.a : Ljava/lang/String;
RETURN
MAXSTACK = 2
MAXLOCALS = 1

@Lkotlin/Metadata;(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0003\u0008\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\u0008\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lcom/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test;", "", "()V", "a", "", "getA", "()Ljava/lang/String;", "app_debug"})
// access flags 0x19
public final static INNERCLASS com/github/hailouwang/demosforapi/kotlin/InnerObjectTest$Test com/github/hailouwang/demosforapi/kotlin/InnerObjectTest Test
// compiled from: InnerObjectTest.kt
}
*/