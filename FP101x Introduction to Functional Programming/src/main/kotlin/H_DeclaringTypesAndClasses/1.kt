package H_DeclaringTypesAndClasses

// type declarationsn can be used to make other types easier to read

// can be nested, but cannot be recursive(no self reference)

typealias Pos = Pair<Int, Int>

typealias Trans = (Pos) -> Pos

fun a(): Trans {
    Pos(1, 2)
    return { p: Pos -> p }
}