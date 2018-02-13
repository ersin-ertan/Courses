package F_FunctionalParsersAndMonads

// Arithmetic expressions
// simple form of expressions bulit up from single digits using operations of addition and multiplication with parentheses

// first we write a context free grammar:
// expr -> term '+' expr | term
// term -> factor '*' term | factor
// factor -> digit | '(' expr ')'
// digit -> '0' | '1' ... | '9'


// massage the grammar
// expr -> terrm ('+' expr | Epsilon(empty parser))
// term -> factor ('*' term + epsilon)

// expr = do t <- term
        // do char '+'
        // e <- expr
        // returnn (t + e)
        // +++return t

// parser combinators are like writing a dsl

// TODO() make functional parser