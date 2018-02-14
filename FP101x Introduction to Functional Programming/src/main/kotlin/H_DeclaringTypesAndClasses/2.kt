package H_DeclaringTypesAndClasses

// Recursive Types
// new types can be declared in terms of themselves then it is recursive

// data Nat = Zero | Succ Nat
// Nat is a new type, with constructors Zero :: Nat and Succ :: Nat -> Nat

// homomorphism
// Succ ( succ(succ zero))
// 1+(1+(1+0))

//class Zero
//class Succ<T>
//typealias Nat = Zero | Succ<Nat> ?????