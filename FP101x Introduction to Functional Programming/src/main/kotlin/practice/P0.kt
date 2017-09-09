package practice

import p
import java.lang.reflect.Type

private fun opposite(b:Boolean) = !b
private fun isUpper(c:Char) = c.isUpperCase()

private fun functionParamAndOut() {

    fun a(a:Int):(Int) -> Int = { input -> a + input }
    fun b(a:Int):(Int) -> Int = { a + it }
    fun c(a:Int):(b:Int) -> Int = { c -> c + a } // value a is captured in the closure

    c(1)(2) // notice that 'b' is from above input, with no relation to fun c = function variables

    c(
            c(
                    c(1)(2)
            )(3)
    )(4)

    fun d(a:Int):(Int) -> Int = { c:Int -> c + a }


    // this can handle null values without explicitly stating it
    fun <F, S> List<F>.zipSmallestSize(list:List<S>):List<Pair<F, S>> = List(minOf(this.size, list.size), {
        Pair(this[it], list[it])
    })

    // when you have to input nulls into the list, because the nulls did not come as an external value, the you have
    // to change the type values
    fun <F, S> List<F>.zipLargestSize(list:List<S>):List<Pair<F?, S?>> = List(maxOf(this.size, list.size), {
        //        val first:F? = try{this[it]}catch (aioobe:ArrayIndexOutOfBoundsException){null} // cant be null
        // instead of using above try
        Pair(elementAtOrNull(it), list.elementAtOrNull(it))
    })

    fun <F, S> List<F>.zipLargestSize1(list:List<S>):List<Pair<F?, S?>> = List(maxOf(this.size, list.size)) {
        Pair(elementAtOrNull(it), list.elementAtOrNull(it))
    } // not the placement of the brace brackets, the function is not a param of the List() constructor, but on the
    // outside of creation, this is allowed if the final / only? param is a function, why ? don't know

    val l0 = listOf(1, 2, 3, 4)
    val l1 = listOf(1, 2, 3)
    val l2 = listOf(1, 2, null, 4)

    l0.zipSmallestSize(l0).p()
    l0.zipSmallestSize(l1).p() // will size based on smallest list
    l0.zipSmallestSize(l2).p() /// will populate with null
    println()
    l0.zipLargestSize(l0).p()
    l0.zipLargestSize(l1).p() // needs explicit null handling in the return type
    l0.zipLargestSize(l2).p() // needs explicit null handling in the return type

    println() // using zip1
    l0.zipLargestSize1(l0).p()
    l0.zipLargestSize1(l1).p()
    l0.zipLargestSize1(l2).p()

}

private fun definingFunctions() {

    // expression vs statement - expression will evaluate to something - statement is said to be
    // conditional expression vs statement

    // b + 1 is an expression
    // a = b + 1; is a statement
    // a++; is an expression statement

    fun a() = 1
    fun b() = 2

    // for boolean conditions
    val a = if (true) a() else b()

    when (a) {
        1 -> null
        2 -> null
        is Int -> null
        else -> null
    }

    // when in pattern matching
    fun c(a:Int, b:Int):Int {
        return when {
            a > 1 && b > 1 -> 1
            a < 1 || b > 0 -> 2
            else -> 0
        }
    }

    val func = { i:Int -> i + i }
    val func2 = { i:Int, b:Int -> (a + b).toDouble() } // infers double output type
    func(1).p()
    func2(1,3).p()

    val func1 = { i:Int, b:Int -> Int } // this is a Int.companion Object
    (func1(1, 3) is Int.Companion).p() // is true
}

fun main(args:Array<String>) {
//    functionParamAndOut()
    definingFunctions()
}