package com.example.androiddemo

class Person {
    var name = "hello"
        get() = "jadjkasjdkal"
    val sex = "world"

}

fun main() {
    var person = Person()
    println("name = ${person.name}")//person.name = "aaa"
}