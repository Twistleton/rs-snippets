// declare a implicit value
//
// given name: type = expression
//
// implicit parameters
//
// def fname(parameters)(using parameters): type = expression
//

def welcome(name: String)(using msg: String): Unit = println(s"$msg, $name!")

given greeting: String = "Hello"

welcome("Hugo")                    // Hello, Hugo!


// ----------------------------------------------------------------------------

def welcome(name: String)(using msg: String): Unit = println(s"$msg, $name!")

given greetingGerman: String = "Hallo"
given greetingEnglish: String = "Hello"
given greetingEspania: String = "Hola"

welcome("Hugo")(using greetingGerman)        // Hallo, Hugo!
welcome("Hugo")(using greetingEnglish)       // Hello, Hugo!
welcome("Hugo")(using greetingEspania)       // Hola, Hugo!

// the compiler finds more then one value of type String and it cannot pick one unambiguously

welcome("Hugo")

// ==> -- [E172] Type Error: ----------------------------------------------------------
//      1 |welcome("Hugo")
//        |               ^
//        |Ambiguous given instances: both given instance greetingEspania and given instance greetingEnglish match type String of parameter msg of method welcome


// ----------------------------------------------------------------------------

case class Order(id: Int)

object Order {
  given ordering: Ordering[Order] with {
    override def compare(x: Order, y: Order): Int = -x.id.compare(y.id)
  }
}

val orders = List(Order(4), Order(2), Order(3), Order(1), Order(5))

println(orders.sorted)

println(s"min: ${orders.min}")       // min: Order(5)
println(s"max: ${orders.max}")       // max: Order(1)

// ----------------------------------------------------------------------------

case class Person(name: String, age: Int)

trait Show[T] {
  def show(t: T): String
}
object Show {
  given stringShow: Show[String] with {
    override def show(s: String): String = s"*${s.toUpperCase()}*"
  }
  given intShow: Show[Int] with {
    override def show(i: Int): String = i.toString
  }
  given listShow[T]: Show[List[T]] with {
    override def show(l: List[T]): String = s"[${l.mkString(", ")}]"
  }
  given personShow[T]: Show[Person] with {
    override def show(p: Person): String = s"${p.name} (${p.age})"
  }
}

def prettyPrintln[T](t: T)(using s: Show[T]): Unit = println(s.show(t))


prettyPrintln("Hugo")                      // *Hugo"
prettyPrintln(1)                           // 1
prettyPrintln(List("a", "b", "c"))         // [a, b, c]
prettyPrintln(Person("Boris Blank", 59))   // Boris Blank (59)

// ----------------------------------------------------------------------------