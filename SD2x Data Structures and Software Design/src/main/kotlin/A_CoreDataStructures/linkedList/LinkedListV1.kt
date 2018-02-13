package A_CoreDataStructures.linkedList

import p


private data class Node(var value:Int) {
    var next:Node? = null
}

private interface Collection {
    fun insert(node:Node, index:Int? = null)
    fun remove(index:Int? = null)
}

private class LinkedList(initHead:Node? = null):Collection {

    private var tail:Node? = null
    fun getTail() = tail

    private var head:Node? = null
    fun getHead() = head

    private var size = 0
    fun getSize() = size

    init {
        if (initHead != null) insert(initHead)
    }

    override fun insert(node:Node, index:Int?) {

        fun firstEntry() {
            head = node
            tail = node
        }

        fun last() {
            tail!!.next = node
            tail = node
        }

        fun insert() {
            if (index == 0) {
                node.next = head
                head = node
            } else {
                var prev = head
                (1 until index!!).forEach { prev = prev!!.next }
                val temp = prev!!.next
                prev!!.next = node
                node.next = temp
            }
        }

        when {
            (index == null || index == 0) && head == null -> firstEntry()
            index == null || index == size -> last()
            index < 0 -> throw IllegalArgumentException("Cannot insert to negative index($index)")
            index > size -> throw IllegalArgumentException("Cannot insert to index($index) larger than size($size)")
            else -> insert()
        }
        ++size
    }

    override fun remove(index:Int?) {

        fun findPrev():Node? {
            var prev:Node? = head
            val prevPos = index ?: size - 1
            (1 until prevPos).forEach { prev = prev!!.next }
            return prev
        }

        when {
            size == 0 -> throw IllegalArgumentException("Cannot remove from empty list")
            index == null || index == size - 1 -> {
                val prev = findPrev()
                prev!!.next = null
                tail = prev

            }
            index < 0 -> throw IllegalArgumentException("Cannot remove at negative index($index)")
            index >= size -> throw IllegalArgumentException("Cannot remove at index($index) larger than size($size)")
            index == 0 -> head = head!!.next
            else -> {
                val prev = findPrev()
                prev!!.next = prev.next!!.next
            }
        }
        --size
    }

    override fun toString():String {
        return buildString {
            append("[")
            var next:Node? = head
            while (next != null) {
                append(next.value)
                next = next.next
                if (next != null) append(", ")
            }
            append("]")
        }
    }
}


fun main(args:Array<String>) {

    val list = LinkedList()
//    val list = LinkedList(Node(0))

    fun printInfo() {
        list.toString().p()
        "Head: ${list.getHead()?.value}".p()
        "Tail: ${list.getTail()?.value}".p()
        "Size: ${list.getSize()}".p()
        println()
    }

    printInfo()
//    "Initial Size: ${list.getSize()}".p()

    println("Insert")
    list.insert(Node(10))
    printInfo()

    list.insert(Node(11))
    printInfo()

    list.insert(Node(8), 0)
    printInfo()

    list.insert(Node(9), 1)
    printInfo()

    list.insert(Node(13), list.getSize())
    printInfo()

    list.insert(Node(12), list.getSize() - 1)
    printInfo()

    println("Remove")
    list.remove()
    printInfo()

    list.remove(0)
    printInfo()

    list.remove(1)
    printInfo()

    list.remove(list.getSize() - 1)
    printInfo()

    list.remove()
    printInfo()

    list.remove() // doesn't remove the last value correctly
    printInfo()

    try {
        list.insert(Node(99), list.getSize() + 1)
    } catch (e:IllegalArgumentException) {
        println("IllegalArgumentException caught")
    }

    try {
        list.insert(Node(99), -1)
    } catch (e:IllegalArgumentException) {
        println("IllegalArgumentException caught")
    }
}
