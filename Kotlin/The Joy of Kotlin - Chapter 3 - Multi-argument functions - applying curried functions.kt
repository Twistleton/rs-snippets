
val add: (Int) -> (Int) -> Int  = { a -> { b -> a + b} }

typealias IntBinOp = (Int) -> (Int) -> Int

val sub: IntBinOp = { a -> { b -> a - b} }

val mult: IntBinOp = { a -> { b -> a * b} }

fun main() {

    val f = add(39)

    println(f(3))
    // ==> 42

    println(add(5)(3))
    // ==> 8

    println(sub(5)(3))
    // ==> 2

}