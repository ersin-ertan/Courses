package E_HigherOrderFunctions

import p

// other library functions - in haskell function(.) returns the composition of two functions as a single function
// (b->c)(a->b)->(a->c) which may look out of order, but remember function application associates so
// f(g x), f is taking the result of g x, thus b is taking the result of a->b

// to be used sparingly because others must read it

// see@ https://gist.github.com/timyates/2300897

//fun compose<T>( fa: ( T ) -> T, fb : ( T ) -> T ) : ( T ) -> T {
//    return { ( a : T ) : T -> fb( fa( a ) ) }
//}
// not working

// see@ https://discuss.kotlinlang.org/t/function-composition-in-standard-library/1683

private val l = listOf<Int>(1, 2, 3)
private val listEven = listOf<Int>(2, 4)
private val takeWhileList = listOf(1, 1, 1, 0, 0, 0, 1, 1, 1)

fun main(args:Array<String>) {

    // alternative for now
    listOf<Int>(1, 2, 3).map(addOne).map({ it * 10 }).p()

    val funIsEven = { i:Int -> i % 2 == 0 }

    all(l, funIsEven).p()
    all(listEven, funIsEven).p()

    val funHasOne = { i:Int -> i == 1 }
    any(listEven, funHasOne).p()
    any(l, funHasOne).p()

    takeWhileTrial(takeWhileList, { i -> i == 1 }).p()

    takeWhile(takeWhileList, { i -> i == 1 }).p()

//    dropWhile(takeWhileList, { i -> i == 1 }).p()

}

fun all(list:List<Int>, func:(Int) -> Boolean):Boolean =
        list.foldRight(true, { next:Int, acc:Boolean -> acc && func(next) })

fun any(list:List<Int>, func:(Int) -> Boolean):Boolean =
        list.foldRight(false, { next:Int, acc:Boolean -> acc || func(next) })

fun takeWhileTrial(list:List<Int>, func:(Int) -> Boolean):List<Int> {
    var newList = list
    return newList.foldRight(emptyList(), { next:Int, acc:List<Int> ->
        if (func(next)) acc.plus(next) else {
            newList = list.dropLast(list.size + 1 - acc.size);
            newList
        }
    })
}

fun takeWhile(list:List<Int>, func:(Int) -> Boolean):List<Int> = list.foldRight(emptyList(),
        { next:Int, acc:List<Int> -> if (func(next)) acc.plus(next) else emptyList() })

// could get this one see@ https://stackoverflow.com/questions/46063844/how-to-implement-dropwhile-recursively-using-foldright-in-kotlin

//fun dropWhile(list:List<Int>, func:(Int) -> Boolean):List<Int> {
//    var yielding = false
//    return list.foldRight(emptyList(),
//            { next:Int, accumList:List<Int> ->
//                return if (yielding) accumList.plus(next)
//                else if (!func(next)) {
//                    yielding = true
//                    accumList.plus(next)
//                } else accumList
//            }
//    )
//}


// official implementation
/*
public inline fun <T> Iterable<T>.dropWhile(predicate:(T) -> Boolean):List<T> {
    var yielding = false
    val list = ArrayList<T>()
    for (item in this)
        if (yielding)
            list.add(item)
        else if (!predicate(item)) {
            list.add(item)
            yielding = true
        }
    return list
}*/
