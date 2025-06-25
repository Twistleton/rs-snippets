// - default arguments -----------------------------------------------------------------------------------------------------------------
//
//
//

fun <T> joinToString(collection: Collection<T>, separator: String = ",", prefix: String  = "[",  postfix: String = "]"): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, prefix = "(", separator = "|", postfix = ")" ))
}

// - extension functions ---------------------------------------------------------------------------------------------------------------
//
//   a function that can be called as a member of a class but is defined outside of it
//

fun String.lastChar(): Char = this.get(this.length - 1)

fun main() {
    val name = "HUGO"
    println(name.lastChar())
}

// - varargs ---------------------------------------------------------------------------------------------------------------------------

fun foo(vararg values: String) {
    val list = listOf<String>(*values)
    println(list)
}

fun main() {
    val arr = arrayOf("A", "B", "C")
   	foo(*arr)
}