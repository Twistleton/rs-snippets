fun f(a: Int): (Int) -> Int {
    return fun(b: Int): Int {
        return a + b
    }
}

fun main() {

    val g = f(39)
    val result = g(3)

    println(result)     // 42

}