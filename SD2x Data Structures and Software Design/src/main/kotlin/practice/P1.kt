package practice

import p

private class P1 {

    class LinkedList {

        data class Node(val v: Any, var n: Node? = null)

        var head: Node? = null
        var tail: Node? = null

        var size = 0

        // only called when size > 0, to return a non null Node
        private fun findPrevNode(i: Int): Node {
            if (size == 0) throw IllegalStateException("No element is the list, cannot return Node")
            return when (i) {
                1 -> head!!
                size -> tail!!
                else -> {
                    var p = head!!
                    repeat(i - 1) { p = p.n!! }
                    p
                }
            }
        }

        fun add(v: Any, i: Int = size): Boolean =
                if (i < 0 || i > size) false
                else {
                    if (size == 0) Node(v).let { head = it; tail = it }
                    else findPrevNode(i).let { it.n = Node(v, it.n) }
                    size++
                    true
                }


        fun remove(i: Int = size - 1): Boolean =
                if (head == null || i < 0 || i >= size) false
                else {
                    if (i == 0) {
                        head = head?.n
                        if (head == null) tail = null
                    } else {
                        findPrevNode(i).let {
                            it.n = it.n?.n
                            if (i == size - 1) tail = it
                        }
                    }
                    size--
                    true
                }


        operator fun contains(v: Any): Boolean {
            var cur = head
            while (cur != null) {
                if (cur.v == v) return true
                cur = cur.n
            }
            return false
        }

        override fun toString(): String = buildString {
            append("[")
            head?.let {
                append(it.v)
                var c = it.n
                while (c != null) {
                    append(", ")
                    append(c.v)
                    c = c.n
                }
            }
            append("]")
        }
    }

    fun test() {

        val l = LinkedList()
        l.add(1)
        l.p()
        l.add(2)
        l.p()
        l.add(3)
        l.p()
        l.add(0, 0)
        l.p()
        l.add(10, 1)
        l.p()
        l.size.p()

        (0 in l).p()
        (3 in l).p()
        (11 in l).p()

        l.remove()
        l.p()
        l.remove(l.size - 1)
        l.p()
        l.remove(0)
        l.p()
        l.add(11)
        l.p()
        l.remove(1)
        l.p()
    }


}

fun main(a: Array<String>) {

    P1().test()

}