package week01

import mooc.EdxIO

fun main(args:Array<String>) {
    EdxIO.create().use { io ->

        val matrix = List(3) { List(3) { io.nextInt() } }
        println(matrix)

    }
}