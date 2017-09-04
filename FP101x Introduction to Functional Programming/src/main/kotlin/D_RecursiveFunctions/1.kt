package D_RecursiveFunctions

import p
import kotlin.system.measureNanoTime

// recursion - natural way to define functions, may use mathematical induction to prove properties of the function

// tail call elimination -

fun factorialRecurse(int:Int):Int = if (int == 0) 1 else int * factorialRecurse(int - 1)
// will stack overflow with negative numbers

fun factorial(int:Int):Int {
    var product = 1
    (int downTo 1).forEach { product *= it }
    return product
}

fun factorialSequence(int:Int):Int {
    var product = 1
    (int downTo 1).asSequence().forEach { product *= it }
    return product
}

// canbe used to define functions on lists
fun listRecursion(intList:List<Int>):Int {
    if (intList.isEmpty()) return 1
    return intList.get(0) * listRecursion(intList.subList(1, intList.size))
}

// why is recursion useful - some functions like factorial are simple to define in terms of other functions
// many functions can naturally be defined in terms of themselves

fun length(list:List<Int>):Int {
    if (list.isEmpty()) return 0
    return 1 + length(list.drop(1))
}

fun reverse(list:List<Int>):List<Int> {
    if (list.isEmpty()) return list
    return listOf<Int>(list.last()) + reverse(list.dropLast(1))
}

// multi argument recursion

fun multiZip(a:List<Int>, b:List<Int>):List<Pair<Int, Int>> {
    if (a.isEmpty() || b.isEmpty()) return listOf()
    return listOf(Pair(a.first(), b.first())) + multiZip(a.drop(1), b.drop(1))
//    return listOf(Pair(a.first(), b.first())).union(multiZip(a.drop(1), b.drop(1))).toList()
}

fun main(args:Array<String>) {

    measureNanoTime {
        factorialRecurse(200)
    }.p()

    measureNanoTime {
        factorial(200)
    }.p()

    measureNanoTime {
        factorialSequence(200)
    }.p()

    listRecursion(listOf(1, 2, 3, 4)).p()

    length((1..10).toList()).p()

    reverse(listOf(1, 2, 3, 4)).p()

    multiZip(listOf(1, 2, 3), listOf(10, 20, 30, 40, 50)).p()
}