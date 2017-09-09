package A_TypesAndClasses

import p


// type classes and polymorphic functions

// polymorphic(of many forms) - a function if its type contains one or more type variables

// personal implementations

fun <T:Collection<Any>> length(col:T):Int {
    var count = 0
    col.forEach { count++ }
    return count
}

fun first(col:Collection<Any>):Class<Any> = col.iterator().next().javaClass

fun head(col:Collection<Any>):Any = col.iterator().next() // should throw an exception if empty

fun take(n:Int):(List<Any>) -> List<Any> = { it.subList(0, n) }

fun zip(a:List<Any>, b:List<Any>):List<Any> {
    return List(maxOf(a.size, b.size)) {

        fun get(l:List<Any>):Any? = if (l.size > it) l.get(it) else null
        /**
         * see elementAtOrNull(it), used in practice.P0.kt
         *
         */

        Pair(get(a), get(b))
    }
}

fun id(any:Any) = any.javaClass

// overloaded functions - a polymorphic function whose type contains one or more class constraints
// type constraint for Int

/*fun <T:Number> sum(numList:List<T>):T {
//    numList.sum() // or
    if(numList.size == 1) return numList[0]
    var sum:T = numList[0]
    numList.forEach { sum += it } // Number can't operate with '+' on all of it's subclasses, thus useless overloading
    return sum
}*/

fun main(args:Array<String>) {

    length(listOf(1, 3, 4)).p()
    length(listOf()).p()

    first(listOf('a', 1, "a")).p()

    head(listOf(1, 3)).p()

    take(3)(listOf(1, 2, 3, 4)).p()

    zip(listOf(1, 2, 3), listOf(1)).p()

    id(1).p()
}