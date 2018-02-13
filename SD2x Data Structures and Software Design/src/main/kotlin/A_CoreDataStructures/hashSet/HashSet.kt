package A_CoreDataStructures.hashSet

import p
import java.util.*

private fun String.hc() = count()

private class HashSet(private val _bucketSize:Int = DEFAULTS.BUCKET_SIZE, private val threshold:Double = DEFAULTS.THRESHOLD) {

    private var bucketSize = _bucketSize

    object DEFAULTS {
        const val BUCKET_SIZE:Int = 6
        const val THRESHOLD:Double = 0.75
    }

    private var numOfElements = 0

    private var arrayOfBuckets:Array<LinkedList<String>> = Array(bucketSize, { LinkedList<String>() })

    fun contains(obj:String):Boolean {
        var index = obj.hc() % bucketSize
        return arrayOfBuckets[index].contains(obj)
    }

    fun remove(obj:String):Boolean {
        val removed = arrayOfBuckets[obj.hc() % bucketSize].remove(obj)
        if (removed) --numOfElements
        return removed
    }

    fun add(obj:String):Boolean {


        if (contains(obj) || obj.isEmpty()) return false

        // use count as hashcode
        else arrayOfBuckets[obj.hc() % bucketSize].add(obj)

        ++numOfElements

        val loadPercent:Double = numOfElements.toDouble() / bucketSize
        println("Load percentage = $loadPercent")
        if (loadPercent > threshold) {

            resizeAndRehash()
        }
        return true
    }

    private fun resizeAndRehash() {
        println()
        println("...resizing...".toUpperCase())
        println()

        val tempArrayOfBuckets = arrayOfBuckets

        bucketSize *= 2
        arrayOfBuckets = Array(bucketSize, { LinkedList<String>() })

        tempArrayOfBuckets.forEach {
            it.forEach {
                arrayOfBuckets[it.hc() % bucketSize].add(it)
            }
        }
    }

    fun size() = numOfElements

    fun clear() {

        arrayOfBuckets.forEach { it.clear() }
        numOfElements = 0

        bucketSize = _bucketSize
        arrayOfBuckets = Array(bucketSize, { LinkedList<String>() })
        // resize the hash set, not sure if this in needed
    }

    override fun toString():String {
        return buildString {

            append("{")

            if (numOfElements == 0) {

                append("}")
                return@buildString

            } else {

                arrayOfBuckets.forEach { bucket ->
                    append("[")
                    var bucketNumOfElements = bucket.size

                    bucket.forEach {

                        --bucketNumOfElements
                        append(it)

                        if (bucketNumOfElements >= 1) append(", ")

                    }
                    append("]")
                }
            }
            append("}")
        }
    }
}

fun main(args:Array<String>) {

    val hs = HashSet()
    print("Empty hashSet")
    hs.toString().p()

    println("Putting elements")
    hs.apply {
        fun a(s:String) {
            add(s)
            toString().p()
        }
        a("dog")
        a("cat")
        contains("cat").p()
        contains("fish").p()
        a("fish")
        a("elephant")
        a("woodchuck")
        a("woodchuckers")
        a("bat")
        a("dolphin")
        a("lion")
        // after lion insertion, and resize, woodchuckers moves from pos 0, to 12
        a("tiger")
        remove("pig").p()
        a("porcupine")
        a("turtle")
        a("orangutans")
        a("")// this can be deceiving when debugging, don't allow it as input
        print("""contains "" ? """); contains("").p()
        a(" ")
        remove(" ").p()
        toString().p()
        size().p()
        remove("dog").p()
        size().p()
        toString().p()

        println()
        println("...CLEARING...")
        println()

        clear()
        toString()
        a("new")


    }

}