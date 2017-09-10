package C_ListComprehensions


// Write code that manipulates collections, most basic keywords for, do, while are to iterate over collections
//
// Sets are the most pure form of collection types but are awkward to work with because duplicate elements are removed,
// thus you need equality to deal with sets. In functional languages, its hard to decide the equality of functions.

// Java list streams can only access the elements one by one.

// Set comprehensions - in mathematics the comprehension notation can be used to construct new sets from old

// {x^2 | x E {...5}} // the set of 1,4,9, 16 of all numbers x^2 such that x is an element of the set {1...5}

// a generator defines how to generate values for x, like: x <-[1..5]

// because of composition, comprehensions can have multiple generators, separated by commas
// [(x,y) | x <- [1,2,3], y <- [4,5] ] which yields [(1,4),(1,5),(2,4),(2,5),(3,4),((3,5)]

// changing the order of the generators changes the order of the elements in the final list

// multiple generators are like nested loops with later generators as more deeply nested loops whose variables change
// value more frequently

// Personal* Think of a circles with different sizes, and position the smaller in the larger, mapping each value of
// the smaller to the one in the bigger as you roll the smaller in the perimeter of the larger

// Dependant Generators - can depend on variables tha are introduced by earlier generators
// [(x,y) | x <- [1..3], y <- [x..3]] the list of all pairs of numbers (x,y) such that x,y are elements of the list
// [1..3] and y >= x

// using dependant generator can define library functions that concatenates a list of lists easily
// concat ::[[a] -> [a]
// concat xss = [x | xs <- xss, x <- xs]

// Guards - list comprehensions can use guards to restrict the values produced by earlier generators aka filters
// [x | x <- [1..10], even x] list of all numbers x such that x is an element of the list 1..10 and is even

// using guard we can define a function that maps a positive integer to its list of factors

// a positive integer is prime if its only factors are 1 and itself, using factors we can define a function that
// decides if a number is prime

