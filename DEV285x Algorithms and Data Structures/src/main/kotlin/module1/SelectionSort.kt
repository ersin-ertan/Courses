package module1

import p
import kotlin.system.measureNanoTime

class SelectionSort {

    fun sort0(list:MutableList<Int>):List<Int> {

        if (list.isEmpty() || list.size == 1) return list

        fun findMinIndex(startIndex:Int):Int {
            var min = list[startIndex]
            var index = startIndex

            (startIndex until list.size).forEach {
                if (list[it] < min) {
                    min = list[it]
                    index = it
                }
            }
            return index
        }

        fun swap(minIndex:Int, currentIndex:Int) {
            val temp = list[currentIndex]
            list[currentIndex] = list[minIndex]
            list[minIndex] = temp
        }

        (0 until list.size).forEach { swap(findMinIndex(it), it) }

        return list
    }

    fun sort1(list:MutableList<Int>):List<Int> {


        list.forEachIndexed { index, it ->
            var swapIndex = 0
            var smallest = list[index]
            (it until list.size).forEachIndexed { index2, itt ->
                if (list[index2] < smallest) {
                    smallest = list[index2]
                    swapIndex = index2
                }
            }
            val temp = list[index]
            list[index] = smallest
            list[swapIndex] = temp
        }

        return list
    }
}

fun main(args:Array<String>) {

    val list = mutableListOf<Int>(1, 2, 3, 6, 4, 4, 2, 3, 5, 1, 5, 9, 7, 25, 8, 5, 2, 4, 8, 2, 58)

    print("sort0\t\t")
    measureNanoTime {
        SelectionSort().sort0(list)
    }.p()

    print("sort1\t\t")
    measureNanoTime {
        SelectionSort().sort1(list)
    }.p()

    print("list.sort()\t")
    measureNanoTime { list.sort() }.p()


}