package week2

import kotlinx.coroutines.experimental.*
import p
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureNanoTime

fun FibRecurse(n:Int):Int {
    return if (n <= 1) n
    else FibRecurse(n - 1) + FibRecurse(n - 2)
}

val fibSequence = buildSequence {
    var a = 0
    var b = 1

    yield(1)

    while (true) {
        val result = a + b
        yield(result)
        a = b
        b = result
    }
}

fun fibNew(n:Int):Int {
    val element = arrayListOf<Int>(0, 1)

    (2..n).forEach { element.add(it, element[it - 1] + element[it - 2]) }
    return element[n]
}


fun main(args:Array<String>) {


    runBlocking {
        var j1 = launch(CommonPool) {
            ("Recursive\t" + measureNanoTime { FibRecurse(100) }).p()

        }
        val j2 = launch(CommonPool) {
            ("Generator\t" + measureNanoTime { fibSequence.take(100).last() }).p()
        }
        val j3 = launch(CommonPool) {
            ("New\t\t\t" + measureNanoTime { fibNew(100) }).p()
        }

        try {
            withTimeout(2, TimeUnit.SECONDS) { j1.join() }
        } catch (e:CancellationException) {
            "Recursive Timeout".p()
        }
        j2.join()
        j3.join()
    }

}