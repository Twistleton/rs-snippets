open class Expr
class Sum(val left: Expr, val right: Expr): Expr()
class Num(val number: Int): Expr()

fun eval(x: Expr): Int =
    when (x) {
        is Sum -> eval(x.left) + eval(x.right)
        is Num -> x.number
        else -> throw IllegalArgumentException()
    }

fun main() {

    // (3 + 2) + 5

    val result = eval(Sum(Sum(Num(3),Num(2)), Num(5)))

    println(result)  // 10
}