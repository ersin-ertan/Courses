package lesson02

fun Any.p() = println(this)
/*
 Lexical analysis - group characters into tokens
 Regular expression can serve as a lexical specification of the language
 Deterministic finite automata(DFA) - an implementation vehicle of regular expressions

 Scanner - read input one char at a time, group characters into tokens, send to parser, also remove whitespace/comments,
 then encode token types and form tuples Pair(token-type, value) and return to parser
*/

val v = 123.454
val tuple = Pair(v.javaClass.typeName, v)

fun main(args:Array<String>) {
    tuple.p() // (double, 123.454) ,(Operator, value = +), (Variable, String = DaysOfTheWeek), etc.
}
/*
 lexical language is a collection(set) of legal strings
 lexical rules are how to form legal strings

 Representations of strings - symbols and alphabet abc123
 Symbol is a valid character in the language, Alphabet is set of legal symbols
 We need metacharacters/metasymbols to have special meaning allowing us to operate on the strings
 Meta char/symbols can be escaped via / to return from their operational ability to their alphasymbolic representation

 Regular expressions follow common order of expressions and allow for groupings.

 State Machines go from one state to another at each state we evaluate the input

 Deterministic finite automata is the simplest model for computing

 A language is called a regular language if some finite automaton recognizes it

 Formal definition of DFA:
 - consists of an Alphabet
 - a set of state: Q
 - a transition function: QxE->Q
 - one start state: q0
 - one or more accepting states: F (sideways u and line symbol) Q

 The language accepted by a DFA is the set of strings such that DFA ends at an accepting state
 Each string is c1,c2...cn with ci element E (the alphabet)
 states are qi = transfer(qi-1, ci) for i=1..n
 ov is an accepting state

Challenge question
DFA for an equal number of '10' and '01' strings

For sake of formating, the | or symbol implies that 0 or 1 must be continued in the self loop, not an or on each self loop
start -> ((1|0)) ----------->if 1 then 0 elseif 0 then 1 -------> ((0|1))
        can self loop           can self loop                   if 1 then 0 elseif 0 then 1, can self loop

*/