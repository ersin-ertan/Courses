package F_FunctionalParsersAndMonads

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.syntax.option.some

// Sequencing - a sequence of parsers can be combined as a single composite parser using the keyword do

// each parser must begin in the same column, the layout rule applies(haskell)

// the values returned by intermediate parsers are discarded by default, but if required can be named using the <- operator
// (haskell)

// the value returned by the last parser is the value returned by the sequence as a whole

// if any parser in a sequence of parsers fails, then the sequence as a whole fails
// the do notation is not specific to the Parser type, but can be used with any monadic type

// advantage of the monad is the do notation / for comprehension

// Derived Primitives - parsing a character that satisfies a predicate

typealias Parser<T> = (String) -> Option<Pair<T, String>>
typealias Failure = (String) -> None
typealias Success<T> = (String) -> Some<T>


fun item(s: String): Option<Pair<Char, String>> = when {
    s.isEmpty() -> None
    else -> Pair(s.first(), s.drop(1)).some()
}

fun <T> failure(s: String): Parser<T> = { st -> None }
//fun <T> success(s: String):(T) -> Parser<T> = { v-> (s).some() }

fun main(args: Array<String>) {

//    item({ st -> if (st.isEmpty()) None else Pair(st.first(), st.drop(1)).some() }, "abc")

}
