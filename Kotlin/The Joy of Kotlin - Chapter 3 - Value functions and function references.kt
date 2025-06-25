class PocketCalculator() {

    // function (member)
    fun double(x: Int): Int = x * 2

    // value function
    val add: (Int, Int) -> Int = { x, y -> x + y }

    // val double: (Int) -> Int = { x -> x * 2 }

    // function reference
	val mutliplyBy2: (Int) -> Int = ::double

  	// val mutliplyBy2: (Int) -> Int = { x -> x * 2 }

}


fun main() {

    val pocketCalc = PocketCalculator()

    println(pocketCalc.add(5, 10))
    println(pocketCalc.double(5))
    println(pocketCalc.mutliplyBy2(22))

}