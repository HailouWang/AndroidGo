package com.github.hailouwang.demosforapi.kotlin.case

fun main() {
    Case2作用域闭包()
}


class Case2作用域闭包 {
    class Parent {
        var name = "Parent"

        override fun toString(): String {
            return "Parent(name='$name')"
        }
    }

    class Child {
        var name = "Child"

        fun op() {
            var age = 19

            var parent = Parent()

            var child = Child()

            parent.run {
                name = "opppppo Method"
                age = 20
            }
            println()
            println("name : $name , age : $age , parent.name : ${parent.name}")
        }

        override fun toString(): String {
            return "Child(name='$name')"
        }
    }
}