package practice

import p
import java.lang.reflect.Type
import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildIterator
import kotlin.coroutines.experimental.buildSequence

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
    func2(1, 3).p()

    val func1 = { i:Int, b:Int -> Int } // this is a Int.companion Object
    (func1(1, 3) is Int.Companion).p() // is true
}

fun listComprehensions() {
//    list comprehension is a way to build a list based on another list

    // close to LINQ in C# var s = from x in Enumerable.Range(0, 100) where x*x > 3 select x*2;
    var a:List<Int> = List(100, {
        if (it * it > 3) it * 2 else -99
    }).filter { i -> i != -99 }
    a.p()
    // ambiguous filter num, may clash with problem domain, and over sized list

    // or use a for loop

    // however there are the generators api with buildSequence() and buildIterator() in the kotlin-stdlib

    fun generators() {

        // standard way
        fun squareNums(nums:List<Int>):List<Int> {
            return List(nums.size, { nums[it] * nums[it] })
        }

        squareNums((1..20).toList()).p()

        // generator
        val squaresSeq = buildSequence {
            (1..20).forEach { yield(it * it) }
        }

        // to allow input, a size constrained sequence
        fun squaresSeqN(n:Int) = buildSequence { (1..n).forEach { yield(it * it) } }


        squaresSeq.p() // returns a sequenceBuilder - but this type is sequence
        squaresSeq.toList().p()

        squaresSeqN(100).take(21).toList().p()

        // infinite sequence
        val infiniteSeq = buildSequence { var aa = 0; while (true) yield(aa++) }

        infiniteSeq.take(27).toList().p()

        // complex yields
        val complex = buildSequence {
            val a = (1..3)
            var b:MutableList<Sequence<Int>> = mutableListOf()

            a.forEach {
                val temp = it * 10
                println("yield from a.forEach with it($it) and temp($temp)")
                yield(temp)
                println()
                b.add(buildSequence {
                    a.forEach {
                        val yieldVal = temp * (temp + it)
                        println("yield from b.forEach with it($it) and yield($yieldVal)")
                        yield(yieldVal)
                    }
                    println()

                })

            }

//            yieldAll(b) // this doesn't work it simply adds the sequences, you need the values from them
            b.forEach {
                yieldAll(it) // this is the sequence, not the value
            }
        }
        // contains the sequence from a and b
//        complex.toList().forEach { println(it) }

        // visualize with this

        val hm = HashMap<Int, List<Int>>()
        val keys = complex.take(3).toList()
        var iter = keys.iterator()

        // shows how each set of 3 b yields', associate with the first three yield of a
        complex.toList().forEach { value ->
            if (value > 100) {
                if (!iter.hasNext()) iter = keys.iterator()
                val key = iter.next()
                hm.put(key, hm.get(key)?.plus(value) ?: listOf(value))
            }
        }
        hm.p()

        buildIterator { yield(1) } // is the same as buildSequence but returns a lazy iterator, instead of a lazy sequence


        //        custom yield logic, by writing a suspending extension to SequenceBuilder, see yieldIfOdd


    }

    generators()

    val customSeq = buildSequence { (1..10).forEach { yieldIfOdd(it) } }
    customSeq.toList().p()
}

suspend fun SequenceBuilder<Int>.yieldIfOdd(i:Int) {
    if (i % 2 != 0) yield(i)
}

private fun List<Int>.recursivePos(match:Int):List<Int> {
    var pos = size - 1 // fold right goes from right to left
    return foldRight(mutableListOf(), { nextValue:Int, acc:MutableList<Int> ->
        if (nextValue == match) acc.add(pos)
        pos--
        acc // don't say return acc, the last value is evaluated as the return for the foldr, else it is evaluated for the whole function
    })
}

fun main(args:Array<String>) {
//    functionParamAndOut()
//    definingFunctions()
//    listComprehensions()

    // foldRight returns the elements processed from right to left, thus our standard form is the result reversed
    listOf<Int>(1, 0, 1, 1, 0).recursivePos(1).asReversed().p()
}