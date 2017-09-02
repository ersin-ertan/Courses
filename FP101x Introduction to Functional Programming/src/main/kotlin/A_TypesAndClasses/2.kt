package A_TypesAndClasses

// A function is a mapping of values from one type to value of another type

fun not(b:Boolean) = !b // boolean -> boolean
fun isDigit(c:Char) = c.isDigit() // char -> boolean

// curried functions - that have multiple arguments that all returning functions as a result

fun add(a:Int):(Int) -> Int = { it -> a + it } // or
fun add0(a:Int):(Int) -> Int = { a + it } // or
fun add1(a:Int):(b:Int) -> Int = { b:Int -> b + a } // or
fun add11(a:Int):(b:Int) -> Int = { b -> b + a } // or
// the value of a is captured in a closure

fun add2(a:Int, b:Int) = a + b


fun main(args:Array<String>) {
    println(add(1)(2)) // curried, take arguments one at a time
    println(add0(1)(3))
    println(add1(1)(4))
    println(add11(1)(5))

    add(1)(  add(2)  (add(3)(4)  )  ) // currying syntax

    println(add2(1, 6)) // not curried
}

// why is currying useful - curried functions are more flexible than functions on tuples, because useful functions
// can often be made by partially applying a curried function