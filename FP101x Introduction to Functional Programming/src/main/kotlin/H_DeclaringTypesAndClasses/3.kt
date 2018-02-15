package H_DeclaringTypesAndClasses

import H_DeclaringTypesAndClasses.Expr.Add
import H_DeclaringTypesAndClasses.Expr.Val

// Arithmetic expressions
// Simple form of expressions built up from integers using addition and multiplication

// for parsers we can define expression using recursive data types
// data Expr = Val Int
//          || Add Expr Expr
//          || Mul Expr Expr

// fold( id as the default, and the expr to evaluate)

// Binary Trees we can define recursive functions on recursive structures


// Pacos example
//sealed class NaturalNum
//
//object Zero : NaturalNum()
//
//class PlusOne<N : NaturalNum>() : NaturalNum()
//
//fun Zero.num() = 0
//
//fun PlusOne<Zero>.num() = 1
//
//@JvmName("two")
//fun PlusOne<PlusOne<Zero>>.num() = 2
//
//fun main(args: Array<String>) {
//    val a: PlusOne<Zero> = PlusOne()
//    val b: PlusOne<PlusOne<Zero>> = PlusOne()
//
//    println(Zero.num())
//    println(a.num())
//    println(b.num())
//
//    when (a) {
//        is Zero -> {
//        }
//        is PlusOne -> {
//        }
//        is NaturalNum -> {
//        }
//    }
//}

sealed class Expr {
    class Val(val value: Int) : Expr()
    class Add(val e1: Expr, val e2: Expr) : Expr()
}

fun Expr.size(): Int = when (this) {
    is Val -> 1
    is Add -> e1.size() + e2.size()
}

fun Expr.eval(): Int = when (this) {
    is Val -> value
    is Add -> e1.eval() + e2.eval()
}

fun main(args: Array<String>) {
    val computation =
            Add(
                    Val(1),
                    Add(
                            Val(2), Val(3)))

    println(computation.eval())
}
