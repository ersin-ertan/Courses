package module1

import p

fun main(args:Array<String>) {

    val list = (1..10).reversed().toMutableList()

    list.p()

    val range = (0 until list.size - 1)

    // for the same reason that you don't have to compare .size times, assuming worst case where the smallest is in the
    // rightmost position, while the inner loop bubbles values to the right, the outer loop implicitly bubbles the
    // largest value to the left, thus you are using index and index + 1, which inverted would be index and index -1

    range.forEach {
        range.forEach {
            if (list[it] > list[it + 1]) {
                val temp = list[it]
                list[it] = list[it + 1]
                list[it + 1] = temp
            }
        }
    }

    list.p()

}