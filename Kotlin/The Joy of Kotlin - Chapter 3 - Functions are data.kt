// function
fun p(x: Int): Int = x + 1

// a function as data
val f: (Int) -> Int = { x -> x + 1 }

fun main() {

    println(p(5))
    println(f(5))

}