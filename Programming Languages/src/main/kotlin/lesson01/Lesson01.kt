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

//    regex()
//    disjunction()
//    aOrNothingViaOptional()
//    zeroOrMore()
//    escapeSequences()
//    lowOrHyphenated()
//    math()
//    quotedStrings()
//    fsmEncoded()
//    phoneNumbers()

    // quiz problems
//    summingNumbers()
//    hyphenatedWords()
    // make fsm for r"(?:a|b)+(?:c|de)*f?"
//    simulatingNondeterminism()
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

fun finiteStateMachines() {
    // start _>(1)_0-9_>(2)_%_>((3)) for the regex [0-9]+%
    //                0-9\/^
    // ()   are states
    // _>   transitions/edges
    // 0-9  is a label for the edge
    // (()) accepting state
    // \/^  self loop

    // if we end up at the accepting state, then the finite state machine matches the regex
}

fun disjunction() { // also known as OR
    Regex("[a-z]+|[0-9]+").matches("htns").p()
    Regex("[a-z]+|[0-9]+").matches("432").p()

    println()
    Regex("ab|[0-9]+").matches("ab").p()
    Regex("ab|[0-9]+").matches("234").p()
    Regex("ab|[0-9]+").matches("abc").p()
    Regex("ab|[0-9]+").matches("a").p()
    Regex("ab|[0-9]+").matches("").p()
}

fun aOrNothingViaOptional() {
    Regex("-?[0-9]+").matches("234").p()
    Regex("-?[0-9]+").matches("-764").p()
}

fun zeroOrMore() {
    Regex("[0-9]*").matches("").p()
    Regex("[0-9]*").matches("234").p()
}

fun escapeSequences() {
    Regex("\\++").matches("+").p()
    Regex("\\++").matches("+++").p()
}

fun lowOrHyphenated() {
    val r = Regex("[a-z]+[-[a-z]+]?") // why isn't this working

    r.matches("a-b").p()
    r.matches("well-a").p()
    r.matches("well-liked").p()
    r.matches("html").p()
    r.matches("a-b-a").p()
    r.matches("a--b").p()
}

fun math() {
    val r = Regex("[a-z]+\\([\\s]*[0-9]+[\\s]*\\)")
    r.matches("cos(4)").p()
    r.matches("cos( 4  )").p()
    r.matches("cos(4345)").p()
    println()
    r.matches("cos  ( 4  )").p()
    r.matches("cos(4eu)").p()
    r.matches("cos(p)").p()
}

fun quotedStrings() {

    // . is any character except linebreak
    // ^ is not
    val sq = "in\"standard\"quotes" // in"standard"quotes

    val dq = "in\"\"double\"\"quotes" // in""double""quotes

    val escBackslash = "escape\\backslash" // escape\backslash

    val escBackslashAndQuote = "escape\\\"backslash\"" // escape\"backslash"

    Regex("[a-z]+[\"][a-z]+[\"][a-z]+").matches(sq).p()

    Regex("[a-z]+[\"][\"][a-z]+[\"][\"][a-z]+").matches(dq).p()
    Regex("[a-z]+[\"]+[a-z]+[\"]+[a-z]+").matches(dq).p()

    // note to capture the backslash you must use three \ to escape the final one, thus four altogether
    Regex("[a-z]+[\\\\][a-z]+").matches(escBackslash).p()

    // note you must first escape the backslash then the quote separately
    Regex("[a-z]+[\\\\][\"][a-z]+[\"]").matches(escBackslashAndQuote).p()

    // you can use two ways to escape a " with either the [\"] or \\\" syntax
    Regex("\\\"").matches("\"").p()

    // or you can use two ways to escape a \" with either the [\\\\][\"] or \\\\\\\" syntax
    Regex("\\\\\\\"").matches("\\\"").p()
}

typealias EdgeMap = kotlin.collections.Map<Pair<Int, Char>, Int>

fun fsmEncoded() {
    // what we want to pass in to the language is the edges, the transformations

//    ex. Regex("a+1+) or start ->(1)_a_>(2)_1_>((3))
//                                      a\/^    1\/^
    val edges = mapOf<Pair<Int, Char>, Int>(
            Pair(1, 'a') to 2,
            Pair(2, 'a') to 2,
            Pair(2, '1') to 3,
            Pair(3, '1') to 3)
    val accepting = listOf<Int>(3)

    fun fsmSim(inputString:String, start:Int, edges:EdgeMap, accepting:List<Int>):Boolean = when {
        inputString.isEmpty() -> start in accepting
        Pair(start, inputString.first()) in edges ->
            with(edges[Pair(start, inputString.first())]) { fsmSim(inputString.drop(1), this!!, edges, accepting) }
        else -> false
    }

    fsmSim("a1", 1, edges, accepting).p()
    fsmSim("aaa111", 1, edges, accepting).p()

    fsmSim("a1a1a1", 1, edges, accepting).p()
    fsmSim("", 1, edges, accepting).p()
    println()

    // q*
    var e = mapOf(Pair(1, 'q') to 1)
    var a = listOf(1, 2)

    fsmSim("", 1, e, a).p()
    fsmSim("q", 1, e, a).p()
    fsmSim("qqqq", 1, e, a).p()
    fsmSim("qqqq2", 1, e, a).p()


    // [a-b][c-d]?
    e = mapOf(Pair(1, 'a') to 2, Pair(1, 'b') to 2, Pair(2, 'c') to 3, Pair(2, 'd') to 3)
    a = listOf(2, 3)

    println()
    fsmSim("a", 1, e, a).p()
    fsmSim("b", 1, e, a).p()
    fsmSim("ad", 1, e, a).p()
    fsmSim("e", 1, e, a).p()
}

// A finite state machine with epsilon transitions(multiple outgoing edges leaving the same state with the same label)
// is ambiguous. A finite state machine accepts a string if there is any path for that string that leads to an accepting
// state.

// Finite state machines are generous, thus if there is at least one possible path, then it will make it work.

fun phoneNumbers() {

//    val r = Regex("[0-9]+[[-[0-9]+]?]*") // not sure why this isn't working
    val r = Regex("[[[0-9]+-]?]*[0-9]+") // still didn't work even though this was the official one
    // there is some problem with the optional and grouping

    r.matches("3").p()
    r.matches("3234").p()
    r.matches("3-3-34-5-6-3-234-234").p()
    r.matches("3-3").p()
    println()
    r.matches("32--234").p()
    r.matches("3-").p()
    r.matches("-3").p()
}

// Non-deterministic fsm are those with epsilon transitions/ambiguous, you have choices.

// Deterministic/lock-step fsm has no epsilon edges or ambiguity and will always present itself with a single answer.

// Every non deterministic fsm has a corresponding deterministic fsm that accepts exactly the same strings.
// Non-deterministic fsm are not more powerful, just more convenient

// thus a start 1 to 2 or 3(these being the epsilon transitions)ending at 4 can be modeled deterministically with:
// start 1,2,3(which contains values for 1, 2 and 3) will end at 4.

// Thus, to build a deterministic machine D, every state in D will correspond to a SET of states in the non-deterministic
// machine

// wrap up - string are seq of characters
// regex is a concise notation for specifying sets of string. More flexible than fixed string matching.
// Finite state machines are a pictoral equivalent to regular expressions
// every non deterministic fsm can be converted to a deterministic FSM
// can simulate a FSM with recursive code

fun summingNumbers() {
    val test_case_input = """The Act of Independence of Lithuania was signed on February 16, 1918, by 20 council members."""
    Regex("[0-9]+").findAll(test_case_input).map { it.value.toInt() }.sum().p()
}

fun hyphenatedWords() {
    val test_case_input = """the wide-field infrared survey explorer is a nasa
infrared-wavelength space telescope in an earth-orbiting satellite which
performed an all-sky astronomical survey. be careful of -tricky tricky-
hyphens --- be precise."""

    val test_case_output = listOf<String>("the", "wide-field", "infrared", "survey", "explorer",
            "is", "a", "nasa", "infrared-wavelength", "space", "telescope", "in", "an",
            "earth-orbiting", "satellite", "which", "performed", "an", "all-sky",
            "astronomical", "survey", "be", "careful", "of", "tricky", "tricky",
            "hyphens", "be", "precise")

    (Regex("[a-z]+-[a-z]+|[a-z]+").findAll(test_case_input).map { it.value }.toList() == test_case_output).p()

    // they match

}

fun simulatingNondeterminism() {
    val edges = mapOf(
            Pair(1, 'a') to listOf(2, 3),
            Pair(2, 'a') to listOf(2),
            Pair(3, 'b') to listOf(4, 3),
            Pair(4, 'c') to listOf(5))
    val accepting = listOf(2, 5)

    fun nfsmSim(input:String, cur:Int, e:Map<Pair<Int, Char>, List<Int>>, accept:List<Int>):Boolean = when {
        input.isEmpty() -> cur in accept
        Pair(cur, input.first()) in e ->
            with(edges[Pair(cur, input.first())]) {
                // List<Int> accepting
                this!!.any { it -> nfsmSim(input = input.drop(1), cur = it, e = e, accept = accept) }
            }
        else -> false
    }

    nfsmSim("abc", 1, edges, accepting).p()
    nfsmSim("aaa", 1, edges, accepting).p()
    nfsmSim("abbbc", 1, edges, accepting).p()
    println()
    nfsmSim("aabc", 1, edges, accepting).p()
    nfsmSim("", 1, edges, accepting).p()


}

fun readingMachineMinds() {
    val edges = mapOf(
            Pair(1, 'a') to listOf(2, 3),
            Pair(2, 'a') to listOf(2),
            Pair(3, 'b') to listOf(4, 2),
            Pair(4, 'c') to listOf(5))
    val edges2 = mapOf(Pair(1, 'a') to 1, Pair(2, 'a') to 2)
    val accepting = 5

//    fun nfsmaccepts(cur:Int, e:Map<Pair<Int, Char>, List<Int>>, accept:Int, visited:List<Int>):String = when {
//        cur == accept -> visited.jo
//        e[cur]-> {
//
//        }
//
//        Pair(cur, input.first()) in e ->
//            with(edges[Pair(cur, input.first())]) {
//                // List<Int> accepting
//                this!!.any { it -> nfsmaccepts(input.drop(1), it, e, accept) }
//            }
//        else -> false
//    }
}