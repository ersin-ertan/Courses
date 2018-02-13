package week01

import mooc.EdxIO
import java.math.BigInteger

// Given two integer numbers, A and B, output A+B^2.

fun main(args:Array<String>) {

    EdxIO.create().use { io ->

        val i1 = io.nextInt()
        val i2 = io.nextInt()

        val power = BigInteger.valueOf(i2.toLong()).pow(2)
        val addition = BigInteger.valueOf(i1.toLong()).plus(power)

        io.println(addition.toString())
    }
}