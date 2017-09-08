package A_CoreDataStructures.linkedList

import p
import java.util.*

private data class Node1(val value:Any, var nextNode:Node1? = null)

private class LinkedList1() {

    private var size = 0
    fun size() = size

    private var head:Node1? = null
    private var tail:Node1? = null

    constructor(initialValue:Any):this() {
        insert(initialValue)
    }

    constructor(valueCollection:Collection<Any>):this() {
        valueCollection.forEach { insert(it) }
    }

    constructor(vararg values:Any):this(values.toList())

    private fun insert(value:Any) {
        val node = Node1(value)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail!!.nextNode = node
            tail = node
        }
        ++size
    }

    fun insert(value:Any, index:Int? = null) {
        if (index == null || index == size) insert(value)
        else if (index < 0 || index > size) throw IllegalArgumentException()
        else if (index == 0) {
            val node = Node1(value)
            node.nextNode = head
            head = node
            ++size

        } else {
            val node = Node1(value)
            var prev = head
            (1 until index).forEach { prev = prev!!.nextNode }
            val temp = prev!!.nextNode
            prev!!.nextNode = node
            node.nextNode = temp
            ++size
        }
    }

    fun remove():Any {
        var toReturn:Node1?
        when (size) {
            0 -> throw IllegalStateException()
            1 -> {
                toReturn = head
                head = null
                tail = null
            }
            else -> {
                var prev = head
                while (prev!!.nextNode!!.nextNode != null) prev = prev.nextNode
                toReturn = prev.nextNode
                prev.nextNode = null
                tail = prev
            }
        }
        --size
        return toReturn!!.value
    }

    fun get(index:Int):Any {
        if (index < 0 || index >= size) throw IllegalArgumentException()
        var current = head
        (0 until index).forEach { current = current!!.nextNode }
        return current!!.value
    }

    fun contains(value:Any):Boolean {
        if (size == 0) return false
        else if (head!!.value == value) return true
        else {
            var current = head
            while (current != null) {
                if (current!!.value == value) return true
                current = current!!.nextNode
            }
            return false
        }
    }

    override fun toString():String =
            buildString {
                append("[")
                if (head != null) {
                    var node = head
                    (0 until size).forEach {
                        append(node!!.value)
                        if (node!!.nextNode != null) append(", ")
                        node = node!!.nextNode
                    }
                }
                append("]")
            }
}

fun main(args:Array<String>) {
//    val list = LinkedList1(1, 2, 3)
//    val list = LinkedList1(listOf(1, 2))
    val list = LinkedList1()

    list.insert(0)
    list.toString().p()

    list.insert(1, 0)
    list.toString().p()

    list.insert(2, 1)
    list.toString().p()

    list.insert(3, 3)
    list.toString().p()

    list.insert(4, 3)
    list.toString().p()

    list.contains(0).p()
    list.contains(10).p()

    // 0..4 or
    (0 until 5).forEach { print(list.get(it).toString() + " ") }

    println()

    (1..5).forEach {
        print("Removing ")
        list.remove().p()
        list.toString().p()
    }

//    list.remove()
}