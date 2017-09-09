package B_DefiningFunctions

// Defining functions

// Conditional expressions - functions can be defined using conditional expressions, which are different from
// conditional statements

val a = if (1 > 2) 3 else 4 // conditional expression


// not quite conditional statement, but is null safe call with else syntax
val aa = null
val b = aa?.toString() ?: "null"

// guarded equation - alternative to conditional, functions can also be defined using guarded equations
// function starts with a condition
// abs n | n >= 0 = n
//       | otherwise = -n

// pattern matching - many functions have a particular clear definition using pattern matching on their arguments
// kotlin doesn't have true pattern matching, but when comes close
val c = when (a) {
    1 -> "equal" // equality
    3, 2 -> 3 // multi case checks
    is Number -> 4 // type checking
    in (1..10) -> 5 // in range checks
    else -> 6
}

fun f() {
    when { // no else needed if a statement
        1 == 3 -> f()
        4 == 5 -> f()
    }
}

// sealed is a sort of union type, using is to match
// we can use when and override equals()

// in scala pattern matching, you can match against values inside a structure, and extract values from the matched exp
// using data class Person
/*
when(person){
    Person("sam", age) -> "matches all sams that have age available"
    Person(name, _) -> "matches all persons, maxes name a parameter, ignores age"
}*/
// another example
//abstract class FileOrFolder
//
//class File:FileOrFolder()
//class Folder:FileOrFolder()
//class Selection:FileOrFolder()
//
//var selection:FileOrFolder = Selection()
//fun changeSelection(item:FileOrFolder, selected:Boolean) {
//    selection = when {
//        item is Folder && selected -> selection + item
//        item is Folder && !selected -> selection - item
//        item is File && selected -> selection + item
//        item is File && !selected -> selection - item
//        else -> throw Exception()
//    } // error + and - unresolved references,  receiver type mismatch
//} // so we do
//
//operator fun Selection.plus(f:FileOrFolder) = {}
//operator fun Selection.minus(f:FileOrFolder) = {}
//
//fun changeSelection1(item:FileOrFolder, selected:Boolean) {
//    if (selected) selection += item
//    else selection -= item
//}

// look into operator modifier


// List Patterns - lists need a different form in pattern matching (cons form)