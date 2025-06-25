fun triple(list: List<Int>): List<Int> = list.map{ a -> a * 3 }

// fun List<Int>.product(): Int = this.fold(1) { a, b -> a * b }

// fun List<Int>.product(): Int = this.fold(1) { a: Int, b: Int -> a * b }

fun List<Int>.product(): Int = this.fold(1) { a, b ->
    val result = a * b
    result
}

fun main() {

    val xs = listOf(3, 5, 7, 10)

    val ys = triple(xs)

    val zs = ys.product()

    println(ys)
    println(zs)

}