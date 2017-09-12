package week1

import p
import kotlin.system.measureNanoTime

fun findMax1(first:Int, list:List<Int>):Int {
    return if (list.size == 1) list[0] * first
    else {
        val mList = list.toMutableList()
        while (mList.contains(first)) {
            mList.remove(first)
        }
        (mList.max() ?: 1) * first
    }
}

fun findMax2(first:Int, list:List<Int>):Int {
    return if (list.size == 1) list[0] * first
    else {
        var max = list[0]
        list.forEach { if (it > max) max = it }
        first * max
    }

}

fun main(args:Array<String>) {

    val first = 10
    var list = listOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
    measureNanoTime { findMax1(first, list) }.p()
    measureNanoTime { findMax2(first, list) }.p()

    list = (1..1000000).toList()
    measureNanoTime { findMax1(first, list) }.p()
    measureNanoTime { findMax2(first, list) }.p()

}
