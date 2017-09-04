package D_RecursiveFunctions

import p

// Quicksort - sort a list recursively by decomposing the list, recursively sorting the lists, and combining the results

// two rules - if empty, then sorted
// take head, sort those greater, sort those smaller, put head in middle

fun quicksort(list:List<Int>):List<Int> = when {

    list.isEmpty() -> list
    else -> {

        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        val first = list.first()

        list.drop(1).forEach { if (it <= list.first()) left.add(it) else right.add(it) }

        quicksort(left) + first + quicksort(right) // implicit return
    }
}

fun replicate(int:Int, nTimes:Int):List<Int> = when {
    nTimes == 0 -> emptyList()
    else -> listOf(int) + replicate(int, nTimes - 1)
}

fun selectNthElement(n:Int, list:List<Int>):Int = when {
    list.isEmpty() || list.size > n -> 0
    n == 1 -> list.first()
    else -> selectNthElement(n - 1, list.drop(1))
}

fun isMember(n:Int, list:List<Int>):Boolean = when {
    list.isEmpty() -> false
    list.first() == n -> true
    else -> isMember(n, list.drop(1))
}

fun main(args:Array<String>) {
//    quicksort(listOf(3, 2, 4, 1, 5)).p()
//    replicate(2, 4).p()
//    selectNthElement(3, listOf(1, 2, 3, 4, 5, 6)).p()
//    isMember(4, listOf(1, 2, 3, 4, 5)).p()
//    isMember(42, listOf(1, 2, 3, 4, 5)).p()
}