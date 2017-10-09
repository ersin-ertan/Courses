package B_DefiningFunctions

// Lambda expressions: functions can be constructed without naming the function

// like a nameless function that takes a number n and returns the result of n+n
val lambda = { i:Int -> i + i }

// lambdas are expressions that denote function

// lambdas are pure functions, but in other languages, they are more like closures because they close over variables
// in the outer scope, and they can have side effects

// usefulness - can be used to give a formal meaning to functions defined using currying
fun add(int:Int):(Int) -> Int = { int + it }

// also useful when defining functions that return functions as results

// and to avoid naming functions that are only referenced once


// sections - an operator written between its two arguments that can bbe converted into a curried function written
// before its two arguments by using parentheses
// 1+2
// vs
// + 1 2
// partial application (1+) 2 or (+2) 1

// useful because functions can be constructed in a simple way using sections
// (1+) successor function, (1/) reciprocation function, (*2) doubling function, (/2) halving

