package E_HigherOrderFunctions

import p

// both filter and map are defined inductively over the list, thus we capture that pattern into yet another higher
// order function to apply it over as many functions over lists as possible

// foldR - recursively descending over the structure of a list, a number of functions on lists can be defined using the
// simple pattern of recursion - f maps the empty list to some value v, and any non empty list to some function circle-plus
// applied to its head of f of its tail

// f is a homomorphism over list by respecting the structure of the list

// foldL -

fun sum(list:List<Int>):Int = when {
    list.isEmpty() -> 0
    else -> list.first() + sum(list.drop(1))
}

fun product(list:List<Int>):Int = when {
    list.isEmpty() -> 1
    else -> list.first() * product(list.drop(1))
}

fun and(list:List<Boolean>):Boolean = when {
    list.isEmpty() -> true
    else -> list.first() && and(list.drop(1))
}


private val l = listOf<Int>(1, 2, 3, 4, 5)

fun foldr(initial:Int, func:(carriedResult:Int, next:Int) -> Int, list:List<Int>):Int {
    if (list.isEmpty()) return initial
    return func(list.first(), foldr(initial, func, list.drop(1)))
}


//fun foldr(initial:Int, func:(List<Int>) -> Int):Int = when {
//    func -> initial
//    else -> initial = func(list.drop(1))
//}

fun main(args:Array<String>) {
    sum(l).p()
    product(l).p()

    and(listOf(true, true, true)).p()
    and(listOf(true, false, true)).p()

//    foldr(0, sum(l)) // couldn't get understand fold enough to implement it

    // see@ http://kotlination.com/kotlin/kotlin-fold-example-list-map#2_foldRight
    l.foldRight(0, { sum, next -> sum + next }).p()
    l.foldRight(10, { sum, next -> sum + next }).p()

    foldr(0, { sum, next -> sum + next }, l).p()

    val productFunc = { product:Int, next:Int -> product * next }
    foldr(1, productFunc, l).p()

    foldr(0, { _, next -> next + 1/*carriedResult is not used, so name it _ */ }, l).p()

    // look into reverse and generic fold
}

// why is foldr useful - sume recursive functions on list, like sum are simpler to define using foldr
// properties of functions defined using foldr can be proven using algebraic properties of foldr, like fusion and banana split ruls
// advanced program optimisations can be simpler if foldr is used in place of explicit recursion