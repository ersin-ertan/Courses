package practice

import p

private class P1 {

    class LinkedList {

        data class Node(val v: Any, var n: Node? = null)

        var head: Node? = null
        var tail: Node? = null

        var size = 0

        fun add(v: Any, i: Int? = null): Boolean {
            when {
                i == null || i == size -> tail?.let { t -> Node(v).let { t.n = it; tail = it } } ?: Node(v).let { tail = it; head = it }
                i < 0 || i > size -> return false
                else ->
                    if (i == 0) head = Node(v, head)
                    else {
                        var p = head!!
                        (1 until i).forEach { p = p.n!! }
                        p.n = Node(v, p.n)
                    }
            }
            size++
            return true
        }

        fun remove(i: Int? = null): Boolean {
            if (head == null || (i != null && (i < 0 || i >= size))) return false

            val ii = i?.let { i } ?: size - 1
            if (ii == 0) {
                head = head!!.n
                if (head == null) tail = null
            } else {
                var p = head!!
                (1 until ii).forEach { p = p.n!! }
                p.n = p.n?.n
                if (ii == size - 1) tail = p
            }

            size--
            return true
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