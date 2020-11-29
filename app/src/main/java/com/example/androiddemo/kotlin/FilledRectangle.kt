package com.example.androiddemo.kotlin

class FilledRectangle :Rectangle() {
    override fun draw() {
        super.draw()
        println("filling the rectangle")
    }

    val fillColor: String get() = super.borderColor

    inner class Filler {
        fun fill() {

        }

        fun drawAndFill() {
            super@FilledRectangle.draw()
            fill()
            println("drawn a filled rectangle with color ${super@FilledRectangle.borderColor}")
        }
    }
}