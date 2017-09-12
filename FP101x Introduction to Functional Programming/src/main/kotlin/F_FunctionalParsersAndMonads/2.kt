package F_FunctionalParsersAndMonads

// Sequencing - a sequence of parsers can be combined as a single composite parser using the keyword do

// each parser must begin in the same column, the layout rule applies(haskell)

// the values returned by intermediate parsers are discarded by default, but if required can be named using the <- operator
// (haskell)

// the value returned by the last parser is the value returned by the sequence as a whole

// if any parser in a sequence of parsers fails, then the sequence as a whole fails
// the do notation is not specific to the Parser type, but can be used with any monadic type

// Derived Primitives - parsing a character that satisfies a predicate