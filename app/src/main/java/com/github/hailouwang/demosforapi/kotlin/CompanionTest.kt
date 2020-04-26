package com.github.hailouwang.demosforapi.kotlin

class CompanionTest {
    companion object{
        val a = "6666"
    }
}

// 相当于
//public class CompanionTest {
//    private static String a = "666";
//
//    public static String getA(){
//        return a;
//    }
//}

/**
public final class com/github/hailouwang/demosforapi/kotlin/CompanionTest {


// access flags 0x1
public <init>()V
L0
LINENUMBER 3 L0
ALOAD 0
INVOKESPECIAL java/lang/Object.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x1A
private final static Ljava/lang/String; a = "6666"
@Lorg/jetbrains/annotations/NotNull;() // invisible

// access flags 0x8
static <clinit>()V
NEW com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion
DUP
ACONST_NULL
INVOKESPECIAL com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion.<init> (Lkotlin/jvm/internal/DefaultConstructorMarker;)V
PUTSTATIC com/github/hailouwang/demosforapi/kotlin/CompanionTest.Companion : Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion;
L0
LINENUMBER 5 L0
LDC "6666"
PUTSTATIC com/github/hailouwang/demosforapi/kotlin/CompanionTest.a : Ljava/lang/String;
RETURN
MAXSTACK = 3
MAXLOCALS = 0

// access flags 0x19
public final static Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion; Companion

// access flags 0x1019
public final static synthetic access$getA$cp()Ljava/lang/String;
L0
LINENUMBER 3 L0
GETSTATIC com/github/hailouwang/demosforapi/kotlin/CompanionTest.a : Ljava/lang/String;
ARETURN
L1
MAXSTACK = 1
MAXLOCALS = 0

@Lkotlin/Metadata;(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2={"Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest;", "", "()V", "Companion", "app_debug"})
// access flags 0x19
public final static INNERCLASS com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion com/github/hailouwang/demosforapi/kotlin/CompanionTest Companion
// compiled from: CompanionTest.kt
}


// ================com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion.class =================
// class version 50.0 (50)
// access flags 0x31
public final class com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion {


// access flags 0x11
public final getA()Ljava/lang/String;
@Lorg/jetbrains/annotations/NotNull;() // invisible
L0
LINENUMBER 5 L0
INVOKESTATIC com/github/hailouwang/demosforapi/kotlin/CompanionTest.access$getA$cp ()Ljava/lang/String;
ARETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x2
private <init>()V
L0
LINENUMBER 4 L0
ALOAD 0
INVOKESPECIAL java/lang/Object.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x1001
public synthetic <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
L0
LINENUMBER 4 L0
ALOAD 0
INVOKESPECIAL com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion; L0 L1 0
LOCALVARIABLE $constructor_marker Lkotlin/jvm/internal/DefaultConstructorMarker; L0 L1 1
MAXSTACK = 1
MAXLOCALS = 2

@Lkotlin/Metadata;(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0003\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\u0008\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lcom/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion;", "", "()V", "a", "", "getA", "()Ljava/lang/String;", "app_debug"})
// access flags 0x19
public final static INNERCLASS com/github/hailouwang/demosforapi/kotlin/CompanionTest$Companion com/github/hailouwang/demosforapi/kotlin/CompanionTest Companion
// compiled from: CompanionTest.kt
}
*/