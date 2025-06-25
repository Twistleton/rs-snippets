private def containsOnlyDigits(phoneNumber: String): Boolean = phoneNumber.forall(_.isDigit)

private def hasExpectedSize(phoneNumber: String): Boolean = phoneNumber.length >= 5

def validatePhoneNumber(phoneNumber: String): Either[String, String] =
  if (!containsOnlyDigits(phoneNumber)) Left("A phone number should only have digits")
  else if (!hasExpectedSize(phoneNumber)) Left("Unexpected number of digits! A number has at least 5 digits")
  else Right(phoneNumber)


val result = validatePhoneNumber("123-456")

result match {
  case Left(message) => println(s"invalid phone number: $message")
  case Right(number) => println(s"valid phone number: $number")
}

// -------------------------------------------------------------------------------------------------------------

def sqrt(n: Int): Either[String, Double] = {
  if (n <= 0) Left("negative number not possiable")
  else Right(Math.sqrt(n))
}

def sqrtOrZero(n: Int): Double = {
  sqrt(n) match {
    case Left(m) => 0.0
    case Right(n) => n
  }
}

println(sqrtOrZero(25))

// -------------------------------------------------------------------------------------------------------------

case class Pass(score: Int) {
  require(score >= 60 && score <= 100, "Invalid pass: score must be between 60 and 100")

  def toPercentage: Double = score / 100.0
}
def mark(score: Int, msg: Option[String] = None): Either[String, Pass] =
  if (score >= 60) Right(Pass(score))
  else Left(msg.getOrElse("Score below 60"))


def toPercentage(outcome: Either[String, Pass]): Either[String, Double] = outcome.map(_.toPercentage)

val result = toPercentage(mark(70, None))

println(result)

// -------------------------------------------------------------------------------------------------------------

case class Pass(score: Int) {
  require(score >= 60 && score <= 100, "Invalid pass: score must be between 60 and 100")
  def toPercentage: Double = score / 100.0
}

def mark(score: Int, msg: Option[String] = None): Either[String, Pass] =
  if (score >= 60) Right(Pass(score))
  else Left(msg.getOrElse("Score below 60"))

def combine_v1(outcomeA: Either[String, Pass], outcomeB: Either[String, Pass]): Either[String, Pass] = {
  outcomeA.flatMap { passA =>
    outcomeB.map { passB =>
      val averageScore = (passA.score + passB.score) / 2
      Pass(averageScore)
    }
  }
}

def combine_v2(outcomeA: Either[String, Pass], outcomeB: Either[String, Pass]): Either[String, Pass] = {
  for {
    passA <- outcomeA
    passB <- outcomeB
  } yield {
    val averageScore = (passA.score + passB.score) / 2
    Pass(averageScore)
  }
}

val r1 = mark(80, None)
val r2 = mark(90, None)

val result_v1 = combine_v1(r1, r2)
val result_v2 = combine_v2(r1, r2)

println(result_v1)
println(result_v2)