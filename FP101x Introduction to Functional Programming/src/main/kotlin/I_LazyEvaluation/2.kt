package I_LazyEvaluation

import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureTimeMillis

fun sieve(isPrime: Int, ints: Sequence<Int>): Boolean = with(ints.first()) {
    return when {
        isPrime  < 2 -> false
        isPrime == 2 -> true
        isPrime == this -> true
        isPrime.rem(2) == 0 -> false
//        isPrime.and(1) == 0 -> false // same way to check if number is even
        this > isPrime -> false
        else -> sieve(isPrime, ints.filter { n -> n.rem(this) != 0 })
    }
}

fun main(args: Array<String>) {
    val lazySeq = buildSequence { for (i in 3..Int.MAX_VALUE step 2) yield(i) }
    println("Duration = ${measureTimeMillis { println("isPrime(4057) = ${sieve(4057, lazySeq)}") }}ms")
//    (2..200).forEach { println("isPrime($it) = ${sieve(it, lazySeq)}") }
}
