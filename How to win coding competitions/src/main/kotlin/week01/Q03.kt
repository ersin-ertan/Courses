package week01

import mooc.EdxIO
import kotlin.coroutines.experimental.buildSequence


// Fibonacci but with 3 numbers, coroutines work

fun main(args:Array<String>) {
    EdxIO.create().use { io ->

        var a = io.nextInt()
        var b = io.nextInt()
        var c = io.nextInt()
        var n = io.nextInt()

 /*       io.println(when (n) {
            0 -> a
            1 -> b
            2 -> c
            else -> {
                repeat(n - 2) {
                    val t = c + b - a
                    a = b
                    b = c
                    c = t
                    n = t
                }
                n
            }
        })
*/
        io.println(buildSequence {

            yield(a)
            yield(b)
            yield(c)

            while (true) {
                val t = c + b - a
                yield(t)
                a = b
                b = c
                c = t
            }
        }.take(n + 1).last())
    }

}