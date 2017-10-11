package lesson01

// using a sub set of the language to practice concepts

fun Any.p() = println(this)


fun doesfirstMatchSecond(s1:String, s2:String) = s1.substring(0, s1.indexOf(" ")) == s2.substring(s2.indexOf(" ") + 1)

fun main(args:Array<String>) {

    fun intro() {
        "mifune toshirofun".indexOf("fun").p()
        "not here".indexOf("z").p() // -1
        "hello world".indexOfFirst { it == ' ' }.p()

        doesfirstMatchSecond("tim bill", "john tim").p()
        doesfirstMatchSecond("hello tom", "bill john").p()

        ("tim bill".split(" ")[0] == "john tim".split(" ")[1]).p()
    }

    regex()
}

fun regex() {
    // regular: simple strings
    // expression: concise notation, like x = sqrt(4) or 4<x<6 where x maps to a value
    "1".matches(Regex("[1-3]")).p() // all true
    Regex("[1-3]").matches("1").p()
    Regex("[1-3]").matches("2").p()
    Regex("[1-3]").matches("12").p() // false

    println()
    Regex("[1-3]").findAll("1+2=3-2").map { it.value }.toList().p() // else returns generator sequence
    Regex("[1-3]").findAll("1+2=3-2").map { it.value }.toSet().p() // only one 2

    println()
    Regex("[a-c][1-2]").matches("b2").p()
    Regex("[0-9][0-9]").findAll("12345").map { it.value }.toList().p()

    println()
    Regex("[a]+").matches("aaaaaaaa").p()
    Regex("[a]+").matches("aaaaa").p()
    Regex("[a]+").matches("a").p()

    // Regular expressions rule - Maximal munch, thus [0-9]+ will take all of 1776
}

fun finiteStateMachines(){
     // start _>(1)_0-9_>(2)_%_>((3)) for the regex [0-9]+%
    //                0-9\/^
    // ()   are states
    // _>   transitions/edges
    // 0-9  is a label for the edge
    // (()) accepting state
    // \/^  self loop

    // if we end up at the accepting state, then the finite state machine matches the regex
}