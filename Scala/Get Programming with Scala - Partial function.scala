def sqrtOrZero(n: Int): Double = n match {
  case x if x >= 0 => Math.sqrt(x)
  case _ => 0
}

def sqrtOrValue(n: Int): Double = n match {
  case x if x >= 0 => Math.sqrt(x)
  case _ => n
}

// sqrt partial function
val sqrt: PartialFunction[Int, Double] = {
  case x if x >= 0 => Math.sqrt(x)
}

val zero: PartialFunction[Int, Double] = { case _ => 0.0 }
val value: PartialFunction[Int, Double] = { case n => n }

val transform: PartialFunction[String, String] = {
  case s: String if s.startsWith("a") => s.reverse
  case s: String if s.startsWith("s") => s.toUpperCase()
}

val result = sqrt.orElse(zero)(5)

println(result)

println(transform("australien"))
println(transform("schottland"))