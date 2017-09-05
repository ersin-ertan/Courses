package E_HigherOrderFunctions

import p

// higher order if it takes a function as an argument or returns a function as a result

val addOne = { int:Int -> int + 1 }

fun twice(func:(Int) -> Int, int:Int):Int {
    return func(func(int))
}

// why are they useful - common programming idioms can be encoded as functions within the language itself
// domain specific languages can be defined as collections of higher order functions
// algebraic properties of higher order functions can be used to reason about programs

// map - applies a function to every element of a list

fun map(list:MutableList<Int>, func:(Int) -> Int):List<Int> {
    (0 until list.size).forEach { list[it] = func(list[it]) }
    return list
}

fun mapRecursive(list:List<Int>, func:(Int) -> Int):List<Int> = when {
    list.isEmpty() -> emptyList()
    else -> listOf(func(list.first())) + mapRecursive(list.drop(1), func)
}

val isEven = { int:Int -> int % 2 == 0 }

fun filter(list:List<Int>, func:(Int) -> Boolean):List<Int> {
    val newList = mutableListOf<Int>() // is there a better way to do this without the new list?
    list.forEach { if (func(it)) newList.add(it) }
    return newList
}


// defining higher order functions recursively allows patterns to appear to abstract to super higher order functions,
// like from map to fold

fun filterRecursive(list:List<Int>, func:(Int) -> Boolean):List<Int> = when {
    list.isEmpty() -> emptyList()
    else -> {
        val newList = mutableListOf<Int>()
        if (func(list.first())) newList.add(list.first())
        newList + filterRecursive(list.drop(1), func)
    }
}

fun main(args:Array<String>) {

    twice(addOne, 0).p()

    map(mutableListOf(3, 2, 1), addOne).p()

    mapRecursive(listOf(1, 2, 3, 4), addOne).p()

    filter(listOf(1, 2, 3, 4), isEven).p()

    filterRecursive(listOf(1, 2, 3, 4), isEven).p()

}