package I_LazyEvaluation

// Lazy eval
// Hove not looked in detail at how haskell expressions are evaluated

// avaid doing unncessary evaluation
// allaws programs to be more modular
// allows us to program with infinite lists

// evaluating expressns
// expresions are eevaluated or reduced by successively applying definitions until no further simplification is possible

// square n =  n * n
// expresion of square (3+4) can be evaluated using the following sequence of reductions
// square(3+4) square 7 then 7*7 = 49
// square(3+4) (3+4)*(3+4) then 7*(3+4), 7*7 = 49

// two different but termanting ways to evaluating the same expression will always give the same final result

// Reduction Strategies

// At each stage during evaluation of an expression, there may be man possible subexpressions that can be reduced by
// applying a definition.

// redex(reducible subexpression)
// innermost reduction: inner always reduced
// outermost reduction: outer always reduced

// given the def loop = tail loop
// loop must have type list

// evaluate the expression fst(1, loop)

// innermost takes fst(1,loop) == fst(1, tail loop) == fst(1,tail(tail loop))
// it is too strict and will never terminate

// outermost reduction taxes fst(1,loop) == 1
// gives result in one step

// outermost may give a result when innermost fails to terminate
// for a given expression if there exists any reduction sequence that terminates, then the outermost reduction also terminates
// with the same result

// number of reductions
// outermost version is ineffcient, and may require more steps than innermost

// We can share and use pointers to indicate sharing of expressions during evaluation
// Lazy evaluation = outermost evaluation + sharing

// advantages - infinite lists
// lazy eval allows us to program with infdinite lists of values

// consider evaluating the expression head ones using innermost reduction and lazy evaluation, it won't terminate
// we never get the head, but lazy will return the first value in the infinite list
// Using lazy evaluation, expressions are only evaluated as much as required to produce the final result, a potentially
// infinite list is only evaluated as much as required by the context.

// Modular programming
// generate finite lists by taking elements from infinite lists