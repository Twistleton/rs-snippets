case class Circle(x: Double, y: Double, radius: Double)

extension (c: Circle) {
  def circumference: Double = c.radius * math.Pi * 2
}

val aCircle = Circle(2, 3, 5)

val circumference = aCircle.circumference

println(circumference)         // 31.41592653589793