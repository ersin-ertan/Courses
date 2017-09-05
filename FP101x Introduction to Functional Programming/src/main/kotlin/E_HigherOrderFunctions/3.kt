package E_HigherOrderFunctions

import p

// other library functions - in haskell function(.) returns the composition of two functions as a single function
// (b->c)(a->b)->(a->c) which may look out of order, but remember function application associates so
// f(g x), f is taking the result of g x, thus b is taking the result of a->b

// to be used sparingly because others must read it

// see@ https://gist.github.com/timyates/2300897

//fun compose<T>( fa: ( T ) -> T, fb : ( T ) -> T ) : ( T ) -> T {
//    return { ( a : T ) : T -> fb( fa( a ) ) }
//}
// not working

// see@ https://discuss.kotlinlang.org/t/function-composition-in-standard-library/1683

fun main(args:Array<String>) {

    // alternative for now
    listOf<Int>(1,2,3).map(addOne).map(addOne).p()
}