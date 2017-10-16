package week01

import mooc.EdxIO

// Given two integer numbers, A and B, output A+B.

fun main(args:Array<String>) {
    EdxIO.create().use { io -> io.println(io.nextInt() + io.nextInt()) }
}