package com.example.androiddemo.kotlin

open class Rectangle {
    open fun draw() {
        println("drawing a rectangle")
    }

    val borderColor: String get() = "black"
}