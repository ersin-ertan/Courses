package practice

import p

// simple linked list - with head set backing property which will nullify tail when head is set to null, also uses also


private class LinkedList3 {

    data class Node(val value:Any, var nextNode:Node? = null)

    var tail:Node? = null
    var head:Node? = null
        set(value) {
            field = value
            if (value == null) tail = value
        }

    var size = 0

    fun add(value:Any) {
        when {
            head == null -> {
                head = Node(value)
                tail = head
            }
            else -> {
                tail!!.nextNode = Node(value)
                tail = tail!!.nextNode
            }
        }
        size++
    }

    fun remove(value:Any):Boolean = when {
        head == null -> false
        head!!.value == value -> true.also {
            head = head?.nextNode
            size--
        }
        else -> {
            var cur1 = head
            (0 until size - 1).forEach {
                if (cur1!!.nextNode!!.value == value) {
                    cur1!!.nextNode = cur1!!.nextNode?.nextNode
                    if (cur1?.nextNode == null) tail = cur1
                    size--
                    return true
                }
                cur1 = cur1!!.nextNode
            }
            false
        }
    }

    // important to note operator allows 'in'
    operator fun contains(value:Any):Boolean {
        if (head == null) return false
        var cur = head
        do {
            if (cur!!.value == value) return true
            cur = cur.nextNode

        } while (cur?.nextNode != null)
        return false
    }

    fun asList():List<Any> = // may be a good function externalsy, but should not be used for other internal funs
            if (size == 0) emptyList<Any>()
            else {
                var cur = head
                List<Any>(size, {
                    val v = cur!!.value
                    cur = cur?.nextNode
                    v
                })
            }

    override fun toString():String {
        return asList().toString() // remember, we simply need the values as strings, not the space for
        // another list, just a simple traversal
        /*
        buildString {
                        append("[")
                        if (head != null) {
                            var cur = head
                            append(cur!!.value)
                            (1 until size).forEach {
                                append(", ${cur!!.value}")
                                cur = cur?.nextNode
                            }
                        }
                        append("]")
                    }
    }*/
    }
}

fun main(args:Array<String>) {

    val l = LinkedList3()

    l.add(1)
    (1 in l).p() // true
    l.toString().p()
    l.remove(2).p()
    l.remove(1).p()
    l.toString().p()
    l.add(1)
    l.add(2)
    l.remove(2)
    l.p()
}