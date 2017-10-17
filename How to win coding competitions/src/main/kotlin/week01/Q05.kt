package week01

import mooc.EdxIO

fun main(args:Array<String>) {
    EdxIO.create().use { io ->

        val matrix = List(3) { List(3) { io.nextInt() } }
        println(matrix)

        var max = 0

        var map = List(3) { mutableMapOf<Int, Int>() }

        matrix.forEachIndexed outer@ { index, list ->
            list.forEachIndexed inner@ { index1, int ->
                map[index1].compute(index1) { key, old ->
                    if (old != null) old + int else int
                }
            }
        }

//        println(List(3) {
//
//            val radicand = BigDecimal.valueOf(io.nextInt().toLong()).pow(2).plus(
//                    BigDecimal.valueOf(io.nextInt().toLong()).pow(2)).plus(
//                    BigDecimal.valueOf(io.nextInt().toLong()).pow(2))
//
//            BigDecimal.valueOf(Math.sqrt(radicand.toDouble()))
//
//        }.max().toString())
    }
}