package F_FunctionalParsersAndMonads

import p

// Parser: a program that analyses a piece of text to determine its syntactic structure
// Almost every real life program uses some form of parsing to pre-process its input

// parsers can naturally be view as functions: type Parser = String -> Tree
// a function that takes a string and returns some form of tree

// but might not require all of its input string, we also return any unused input: type Parser = String -> (Tree, String)

// a string might be parsable in many ways, including none, so we generalize to a list of results:
// type Parser = String -> [(Tree, String)] , or some languages use maybe type for optional result

// Finally, a parser might not always produce a tree, so we generalize to a value of any type:
// type Parser a = String -> [(a, String)]

// For simplicity, we only consider parsers that either fail and return the empty list of results, or succeed and
// return and singleton list


// Basic Parsers - the parser item fails if the input is empty, and consumes the first character otherwise:

fun basicParser(input:String):List<Pair<Char, String>> = when {
    input.count() == 0 -> emptyList()
    else -> listOf(Pair(input.first(), input.drop(1)))
}

fun failingParser(input:String):List<Any> = emptyList()

fun succeedingParser(input:String):List<Pair<String, String>> = listOf(Pair(input, input))

// a parsing chain can parse or else return an empty list, to which the others in the chain will return empty lists

fun main(args:Array<String>) {
    basicParser("").p()
    basicParser("abc").p()
    succeedingParser("a").p()
    if (failingParser("abc").isEmpty()) succeedingParser("abc").p()
}

// the library Parsing is on the haskell home page

// Parser type is a monad: a mathematical structure that has proved useful for modeling many different kinds of
// computation