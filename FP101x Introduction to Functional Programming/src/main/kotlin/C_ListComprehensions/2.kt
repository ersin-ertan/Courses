package C_ListComprehensions

import p

// the zip function - library function which maps two lists to a list of pairs of their corresponding elements

fun pairs(list:List<Int>):List<Pair<Int, Int>> = List(list.size - 1, {
    // until acts as size -1, thus giving all of the element positions, but we need 1 less than that
    Pair(list.get(it), list.get(it + 1))
})

fun pairsZip(list:List<Int>):List<Pair<Int, Int>> {
    return list.zip(list.subList(1, list.size)) // second param in sub list is exclusive
}

// using pairs we can define a function to see if the elements are sorted in a list
fun sorted(list:List<Int>):Boolean {
    0.until(list.size - 1).forEach { if (it > list.get(it + 1)) return false }
    return true
}

// we can use zip to define a function that returns the list of all positions of a value in a list
fun pos(list:List<Int>, match:Int):List<Int> {
    val outList = mutableListOf<Int>()
    list.zip(0 until list.size).forEach { if (it.first == match) outList.add(it.second) }
    return outList
} // zipping with the index

// String comprehensions - is a sequence of characters enclosed in double quotes, internally they are represented
// as a list of characters

// because strings are special kinds of lists, any polymorphic function that operates on lists can bbe applied to strings

// list comprehensions can be used to define functions on string, like counting the number of lower case letters in a string

private val l = listOf<Int>(1, 2, 3, 4)

fun main(args:Array<String>) {
    pairs(l).p()
    pairsZip(l).p()

    sorted(l).p()
    sorted(l.plus(2)).p()

    pos(listOf(1,0,0,1,0,1,1,0), 0).p()

    "abc".toList().zip(listOf(1,2,3,4)).p()

    "Kotlin".filter { it.isLowerCase() }.count().p()
}
