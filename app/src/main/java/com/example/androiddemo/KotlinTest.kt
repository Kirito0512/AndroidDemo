package com.example.androiddemo
private val test : Int = 5
interface A {
    fun foo() {
        print("A")
    }

    fun bar()
}

interface B {
    fun foo() {
        print("B")
    }

    fun bar() {
        print("bar")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
    }

    override fun bar() {
        super.bar()
    }
}

fun main() {

}