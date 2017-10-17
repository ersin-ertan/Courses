package week01

import mooc.EdxIO

fun main(args:Array<String>) {
    EdxIO.create().use { io ->

        val days = io.nextInt()

        val p = Array<Int>(days, { io.nextInt() })
        val t = Array<Int>(days, { io.nextInt() })

        val dif = Array<Int>(days, { p[it] - t[it] })

        var oneP = false
        var oneT = false

        val ability = p.foldIndexed(0, { i:Int, acc:Int, cur:Int ->
            acc + when {
                cur > t[i] -> {
                    oneP = true
                    cur
                }
                cur < t[i] -> {
                    oneT = true
                    t[i]
                }
                else -> cur
            }
        })

        io.println(when {
            oneP && !oneT -> ability - dif.min()!!
            !oneP && oneT -> ability + dif.max()!!
            else -> ability
        })
    }
}