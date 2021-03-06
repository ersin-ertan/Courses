package practice

import org.jetbrains.annotations.TestOnly
import p
import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureNanoTime

private class LinkedListP(initVal:Any? = null) {

    private val nodeSequence:Sequence<Any?>
        get() {
            return buildSequence {
                var next = head
                if (size == 0) {

                }
                while (true) {
                    if (next == null) yield(null)
                    else {
                        yield(next.value)
                        next = next.next
                    }
                }
            }
        }

    fun asSequence():Sequence<Any?> = nodeSequence.take(size)


    private data class Node(val value:Any, var next:Node? = null)

    private var size:Int = if (initVal != null) 1 else 0

    private var head:Node? = if (initVal != null) Node(initVal) else null
    private var tail:Node? = head

    fun add(value:Any, index:Int? = null):Boolean {
        if (index == null || index == 0) {
            head = Node(value, head)
            if (++size == 1) tail = head
            return true
        } else if (index < 0 || index > size) return false
        else {
            val prev = findPrev(index)
            prev!!.next = Node(value, prev.next?.next ?: null)
        }
        ++size
        return true
    }

    private fun findPrev(index:Int):Node? {
        // because index is refering to where we need to find the previous, asSequence().take(index) will get 'index' amount
        // of elements. The previous of index 1 is index 0, so we need to take 1 element and get the last() element
        // not plus 1 because prev is the index

//        ("sequence " + measureNanoTime { nodeSequence.take(index).last() }).p() // slower

        var prev = head
        for (it in 0 until size - 1) {
            if (it + 1 == index) break
            prev = prev!!.next
        }

        return prev
    }


    // no modification of tail, this is wrong
    fun remove(index:Int? = null):Boolean {
        return when {
            head == null -> false
            index == null -> remove(0)
            index >= size || index < 0 -> false
            size == 1 -> {
                head = null
                tail = null
                --size
                true
            }
            index == 0 -> {
                head = head!!.next
                --size
                true
            }
            else -> {
                var prev = findPrev(index)
                prev!!.next = prev.next?.next ?: null
                --size

                true
            }
        }
    }

    override fun toString():String {
        return buildString {
            append("[")
            var cur = head
            while (cur != null) {
                append(cur.value)
                if (cur.next != null) append(", ")
                cur = cur.next
            }
            append("]")
        }
    }
}

private class LinkedList1() { // Work in process

    private class Node(val value:Any, var next:Node? = null)

    private var head:Node? = null
    private var tail:Node? = null

    private var size = 0
    fun size() = size

    constructor(value:Any):this(listOf(value))

    constructor(vararg varargs:Any):this(varargs.toList())

    constructor(valueCollection:Collection<Any>):this() {
        valueCollection.forEach { add(it) }
    }

    fun add(addValue:Any, index:Int? = null):Boolean = if (index == null) addLast(addValue) else addBefore(addValue, index)

    private fun addBefore(addValue:Any, index:Int):Boolean {
        if (index == 0 || size == 0) {
            head = Node(addValue,
                    if (head != null) head else null)
            if (size == 0) tail = head
        } else if (index == size) {
            addLast(addValue)
        } else {
            val prev = getPrev(index)
            prev.next = Node(addValue, prev.next)
        }
        size++
        return true
    }

    fun addLast(lastValue:Any):Boolean = true.apply {
        if (size++ == 0) {
            tail = Node(lastValue)
            head = tail
        } else {
            tail!!.next = Node(lastValue)
            tail = tail!!.next
        }
    }

    private fun getPrev(index:Int):Node {
        if (index > size || index < 0) throw IllegalArgumentException("getPrev($index) index out of bounds")
        var cur = head
        (0 until index - 1).forEach { cur = cur!!.next }
        return cur!!
    }

    override fun toString():String = buildString {
        append("[")
        if (size > 0) {
            append(head!!.value)
            var cur = head
            while (cur?.next != null) {
                cur = cur.next
                append(", ${cur!!.value}")
            }
        }
        append("]")
    }


}

private class LinkedList2<T>() {

    private data class Node<T>(val value:T, var next:Node<T>? = null)

    private var head:Node<T>? = null
    private var tail:Node<T>? = null

    private var size = 0
    fun size() = size

    val ioobe = IndexOutOfBoundsException()

    constructor(value:T):this(listOf(value))
    constructor(vararg values:T):this(values.toList())
    constructor(collection:Collection<T>):this() {
        collection.forEach { add(it) }
    }


    /*
    * Add values
    * */
    fun add(addValue:T, index:Int? = null):Boolean = when {
        index == null || size == index -> addLast(addValue)
        index < 0 || index > size -> throwIndexOutOfBoundsException(index)
        emptyAdd(addValue) -> true
        else -> {
            val prev = findPrev(index)
            prev.next = Node(addValue, prev.next)
            size++
            true
        }
    }

    fun addFirst(firstValue:T):Boolean = true.apply {
        if (emptyAdd(firstValue))
        else {
            head = Node(firstValue, head)
            size++
        }
    }

    fun addLast(lastValue:T):Boolean = true.apply {
        if (emptyAdd(lastValue))
        else {
            tail!!.next = Node(lastValue)
            tail = tail!!.next
            size++
        }
    }

    /*
    * Remove values
    * */
    fun removeAt(index:Int):T? = when {
        !isValidRemoveIndex(index) -> throwIndexOutOfBoundsException<Nothing>(index)
        index == 0 -> removeFirst()
        index == size - 1 -> removeLast()
        else -> {
            val prev = findPrev(index)
            val removedValue = prev.next!!.value
            prev.next = prev.next?.next
            size--
            removedValue
        }
    }

    fun remove(removeValue:T? = null):Boolean {
        if (removeValue == null) removeLast()
        else if (size == 0) throwIndexOutOfBoundsException<Nothing>(0)

        var cur = head

        (0 until size).forEach {
            if (cur!!.value == removeValue) {
                removeAt(it)
                return true
            }
            cur = cur!!.next
        }
        return false
    }


    fun removeFirst():T {
        if (!isValidRemoveIndex(0)) throwIndexOutOfBoundsException<Nothing>(0)
        val removedValue = head!!.value
        head = head!!.next
        size--
        return removedValue
    }

    fun removeLast():T {
        if (!isValidRemoveIndex(size - 1)) throwIndexOutOfBoundsException<Nothing>(size - 1)
        val prev = findPrev(size - 1)
        val removedValue = prev.next!!.value
        prev.next = null
        tail = prev
        size--
        return removedValue
    }

    /*
    * Others
    * */
    fun contains(containsValues:T):Boolean {
        var cur = head
        (0 until size).forEach {
            if (cur!!.value == containsValues) {
                return true
            }
            cur = cur!!.next
        }
        return false
    }

    /*
    * Private helpers
    * */
    private fun findPrev(index:Int):Node<T> {
        var prev = head!!
        (0 until index - 1).forEach { prev = prev.next!! }
        return prev
    }

    private fun emptyAdd(initialValue:T):Boolean =
            if (size == 0) {
                head = Node(initialValue)
                tail = head
                size++
                true
            } else false

    private fun isValidRemoveIndex(removeIndex:Int?):Boolean = removeIndex == null || removeIndex > 0 || removeIndex < size

    private fun <R> throwIndexOutOfBoundsException(index:Int?):R = throw IndexOutOfBoundsException("$index for ${0..size - 1}")

    @TestOnly private fun headTailSize():String = "\nhead(${head?.value})\ttail(${tail?.value})\tsize(${size})\n"

    /*
    * Data overrides
    * */
    override fun toString():String = buildString {
        append("[")
        if (size > 0) {
            var cur = head
            append(cur!!.value)
            cur = cur.next
            while (cur != null) {
                append(", ${cur.value}")
                cur = cur.next
            }
        }
        append("]")

        // TODO comment out, debugging only
        append(headTailSize())
    }

    override fun equals(other:Any?):Boolean = when {
        other == null || other !is LinkedList2<*> ||
                other.size() != size || other.head != head || other.tail != tail -> false
        else -> {
            val cur = head
            val curO = other.head
            (0 until size).forEach {
                if (cur != curO) {
                    return false
                }
            }
            true
        }
    }

    override fun hashCode():Int {
        return super.hashCode()
    }
}


fun main(args:Array<String>) {

    fun p0() {

        val ll = LinkedListP()
        (0..10000).forEach { ll.add(it) }

        ("sequ\t" + measureNanoTime {

            ll.asSequence().take(100).filter {
                if (it is Int)
                    it % 2 == 0
                else
                    false
            }.map { (it as Int) / 2 }
                    .toList()
        }).p()

        ("list\t" + measureNanoTime {
            ll.asSequence().toList().take(100).filter {
                if (it is Int) it % 2 == 0
                else false
            }.map { (it as Int) / 2 }
        }).p()

        fun fibonacci() = buildSequence {
            var terms = Pair(0, 1)

            // this sequence is infinite
            while (true) {
                yield(terms.first)
                terms = Pair(terms.second, terms.first + terms.second)
            }
        }

        fun fibonacci1() = buildSequence {
            var a = 0
            var b = 1

            // this sequence is infinite
            while (true) {
                yield(a)
                val c = a + b
                a = b
                b = c
            }
        }

        val take = 300_000

        ("Pair " + measureNanoTime {
            fibonacci().take(take).last().p()
        }).p() // faster by a factor of 2


        ("Prim " + measureNanoTime {
            fibonacci1().take(take).last().p()
        }).p()

        ("List " + measureNanoTime {
            var a = 0
            var b = 1
            List(take, {
                val aa = a
                a = b
                b = aa + a
                aa
            }).last().p()
        }).p()
    }

    fun p1() {

        val ll = LinkedList1(1, 2, 4)
        ll.p()
        ll.size().p()
        ll.add(1)
        ll.p()
        ll.size().p()
        print("adding 0 ")
        ll.add(0, 0)
        ll.p()
        ll.size().p()
        ll.add(9, 1)
        ll.p()
        ll.size().p()
        ll.add(8, 2)
        ll.p()
        ll.size().p()
        ll.add(7, ll.size())
        ll.p()
        ll.addLast(99)
        ll.p()

    }

    val l = LinkedList2<Int>()
    l.add(1)
    l.p()
    l.add(2, 1)
    l.p()
    l.add(2, 1)
    l.p()
    l.addLast(3)
    l.p()
    l.addFirst(0)
    l.p()

    l.removeLast()
    l.p()
    l.removeFirst()
    l.p()
    l.add(3)
    l.add(4)
    l.p()
    l.add(5)
    l.p()
    l.remove()
    l.p()

    l.remove(2).p()
    l.p()
    l.remove(2).p()
    l.p()
    l.remove(2).p()
    l.p()

    l.remove(1).p()
    l.p()

    l.add(5)
    l.add(6)
    l.add(7)
    l.add(8)
    l.add(9)
    l.p()
    l.contains(9).p()
    l.contains(10).p()
    l.removeAt(0)
    l.removeAt(l.size() - 1)
    l.p()
    l.removeAt(1)
    l.p()
    l.removeAt(2)
    l.p()
    l.removeAt(2)
    l.p()


}
