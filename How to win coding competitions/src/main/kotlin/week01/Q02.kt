package week01

import mooc.EdxIO
import java.math.BigDecimal
import java.math.BigInteger

// Given two integer numbers, A and B, output A+B^2.

fun main(args:Array<String>) {
//    EdxIO.create().use { io -> io.println(io.nextInt() + Math.pow(io.nextInt().toDouble(), 2.0).toInt()) }
    EdxIO.create().use { io ->
        io.println(
                (BigInteger.valueOf(io.nextInt().toLong()).plus(
                        BigInteger.valueOf(
                                Math.pow(io.nextInt().toDouble(), 2.0).toLong()))).toString()
        )
    }
}