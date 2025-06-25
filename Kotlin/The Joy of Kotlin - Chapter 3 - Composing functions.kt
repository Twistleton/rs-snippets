// Composing functions

// composing function for Int's
// fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { x -> f(g(x)) }

// polymorphic function
// f . g
fun <T, U, V> compose(f: (U) -> V, g: (T) -> U): (T) -> V = { x -> f(g(x)) }

fun main() {

    val fn: (Int) -> Int = { x -> x + 1 }
    val fm: (Int) -> Int = { x -> x * 2 }
    val fo: (Int) -> Int = { x -> x * 3 }

    // composing functions: fn . fm = 1 + (x * 2)
    val fc1 = compose(fn, fm)

    println(fc1(5))
    // ==> 11

    // composing functions: fn . fm . fo = 1 + (2 *  (x * 3))
    val fc2 = compose(fc1, fo)

    println(fc2(5))
    // ==> 31

}