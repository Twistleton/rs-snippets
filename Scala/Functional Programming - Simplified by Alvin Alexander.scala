// https://github.com/alvinj/LearnFunctionalProgrammingBook

// map

def map[A, B](f: A => B, xs: Seq[A]): Seq[B] = xs match
case Nil => Nil
case x :: xs => f(x) +: map(f, xs)

val xs = List(1,2,3)

def double(x: Int) = x * 2

val ys = map(double, xs)           // List(2, 4, 6)

// filter

def filter[A](p: A => Boolean, xs: Seq[A]): Seq[A] = xs match
case Nil => Nil
case x :: xs => if p(x) then x +: filter(p, xs) else filter(p, xs)

val xs = List(1,2,3,4,5,6)

def isEven(x: Int) = x % 2 == 0

val ys = filter(isEven, xs)        // List(2, 4, 6)

// timer

def timer[A](codeBlock: => A) = {
  val startTime = System.nanoTime()
  val result = codeBlock
  val endTime = System.nanoTime()
  (result, (endTime - startTime) / 1000000d)
}

def readFile(filename: String) = io.Source.fromFile(filename).getLines

val (result, time) = timer(readFile("C:/home/arbeitsplan/aktuell/scala3hours11585773601419.pdf")

// write your own control structures

def whilst(testCondition: => Boolean)(codeBlock: => Unit): Unit = {
  if (testCondition) {
    codeBlock
    whilst(testCondition)(codeBlock)
  }
}

var i = 0

whilst(i < 5){
  println("Scala ")
  i = i + 1
}

// write your own control sructures

def ifBothTrue(testConditionA: => Boolean)(testConditionB: => Boolean)(codeBlock: => Unit): Unit = {
  if testConditionA && testConditionB then codeBlock
}

var age = 20
var numAccidents = 0

ifBothTrue(age > 18)(numAccidents == 0) {
  println("Discount!")
}

// using implicit values

def printIntIfTrue(a: Int)(using b: Boolean) =
if b then println(a)

implicit var flag: Boolean = true

printIntIfTrue(42)

// sum - tail recursive

def sum(xs: List[Int], acc: Int = 0): Int = xs match
case Nil => acc
case x :: xs => sum(xs, acc + x)

val xs = List.range(1, 99999)

sum(xs)

// Error handling with Option

import scala.util.control.Exception.*

def makeInt(s: String): Option[Int] = allCatch.opt(s.toInt)

makeInt("42")   // Some(42)
makeInt("one")  // None

// Error handling with Try

import scala.util.{Try, Success, Failure}

def makeInt(s: String): Try[Int] = Try(s.toInt)

val maybeSum = for
  x <- makeInt("5")
y <- makeInt("3")
z <- makeInt("2")
yield
x + y + z

maybeSum match
case Success(n) => println(s"result: $n")                   // result: 10
case Failure(e) => println(s"exception occured: $e")

// Error handling with Either

def makeInt(s: String): Either[Exception, Int] =
  try
    Right(s.toInt)
  catch
case e: NumberFormatException => Left(e)

makeInt("1")           // Right(1)
makeInt("one")         // Left(java.lang.NumberFormatException: For input string: "one")

// Error handling with Either and allCatch

import scala.util.control.Exception.allCatch

def makeInt(s: String): Either[Throwable, Int] =
  allCatch.either(s.toInt)

