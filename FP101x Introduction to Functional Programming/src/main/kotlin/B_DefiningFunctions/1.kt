package B_DefiningFunctions

import p

// Defining Functions

// Functions can be defined using conditional expressions, which are different from conditional statements

val a = if (1 > 2) 3 else 4 // conditional expression


// not quite conditional statement, but is null safe call with else syntax
val aa = null
val b = aa?.toString() ?: "null"

// guarded equation - alternative to conditional, functions can also be defined using guarded equations
// function starts with a condition
// abs n | n >= 0 = n
//       | otherwise = -n

// pattern matching - many functions have a particular clear definition using pattern matching on their arguments
// kotlin doesn't have true pattern matching, but 'when' comes close
val c = when (a) {
    1 -> "equal" // equality
    3, 2 -> 3 // multi case checks
    in (1..10) -> 5 // in range checks
    is Number -> 4 // type checking
    else -> 6
}

sealed class SC {
    class SCA:SC()
    class SCB:SC()
    class SCC:SC()
}

interface SCI
interface SCII

class SCandSCI:SC(), SCI

fun test(a:Any) = when (a) {
    is String, "hello" -> "this is string hello"
    is Number, 2 -> "this is number 2"
    is SC, is SCI, is SCII -> "this is SC and SCI and SCII"
    else -> "don't know"
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

abstract class FileOrFolder

class File:FileOrFolder()
class Folder:FileOrFolder()

class Selection:FileOrFolder()

operator fun FileOrFolder.plus(fileOrFolder:FileOrFolder):FileOrFolder = fileOrFolder
//operator fun FileOrFolder.plusAssign(fileOrFolder:FileOrFolder):Unit = Unit
operator fun FileOrFolder.minus(fileOrFolder:FileOrFolder):FileOrFolder = fileOrFolder
//operator fun FileOrFolder.minusAssign(fileOrFolder:FileOrFolder):Unit = Unit


var selection:FileOrFolder = Selection()

// not multi case checks use OR not and
fun changeSelection1(item:FileOrFolder, selected:Boolean) = when (selected) {
    true, item is File -> "true file"
    true, item is Folder -> "true folder"
    false, item is File -> "false file"
    false, item is Folder -> "false folder"
}

fun changeSelection2(item:FileOrFolder, selected:Boolean) = when {
    item is Folder && selected -> {
    }
    item is Folder && !selected -> {
    }
    item is File && selected -> {
    }
    item is File && !selected -> {
    }
    else -> {
    } // requires else
}

fun changeSelection3(item:FileOrFolder, selected:Boolean) = when {
    item is Folder ->
        when (selected) {
            true -> selection + item
            false -> selection - item
        }
    item is File ->
        when (selected) {
            true -> selection + item
            false -> selection - item
        }
    else -> {
    }
}

fun changeSelection(item:FileOrFolder, selected:Boolean) = when {
    item is Folder && selected -> selection + item
    item is Folder && !selected -> selection - item
    item is File && selected -> selection + item
    item is File && !selected -> selection - item
    else -> File()
//    else -> throw Exception("Not Flie or Folder") // wouldn't have to include this if sealed
}


// this is the best, requires the operators to be defined and working correctly
// https://stackoverflow.com/questions/44558663/overloading-and-operators-for-number-classes
fun changeSelection4(item:FileOrFolder, selected:Boolean) {
    if (selected) selection += item // operator ambiguity both + and += match so I uncommented +=, was generated for me
    else selection -= item
}

// List Patterns - lists need a different form in pattern matching (cons form)


fun main(args:Array<String>) {
    changeSelection1(File(), true).p()
    changeSelection1(Folder(), true).p()
}