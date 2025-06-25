sbt
===
about   // provides general information on the sbt version
console // starts the REPL within a sbt project; all code and dependencies are accessible from the REPL

Giter 8
=======
https://github.com/foundweekends/giter8/wiki/giter8-templates

  ???
===
def myFunc(n: Int) = ???   // Placeholder for future implementation

calculate the power of a number:  2³
====================================
def pow(n: Int, e: Int): Int = {
  def powTail(e: Int): Int = {
    if e == 0 then 1 else n * powTail(e - 1)
  }
  powTail(e)
}

pow(2, 3)

Import syntax
  =============
import scala.concurrent.duration._   // Scala 2
import scala.concurrent.duratoin.*   // alternative syntax in Scala 3
import scala.io.{Source => Src}      // Scala 2
import scala.io.{Source as Src}      // alternative syntax in Scala 3

apply()
=======
From Scala 3, the compiler automatically generates an apply function for all your classes;
you can omit the new keyword even if you haven't manually defined an apply method for it.

new Employee()       // works in all Scala versions
Employee()           // works in Scala 3+

traits
======

class name extends MyTrait
object name extends MyTrait
trait name exends MyTrait

class name extends MyTrait with AnotherTrait
object name extends MyTrait with AnotherTrait
trait name extends MyTrait with AnotherTrait

The trait App
=============

object Main extends App {
  println("Hello world")
}

sealed traits
=============
sealed trait Currency

class USD extends Currency
class EUR extends Currency
class YEN extends Currency

enum
====
enum Country(val code: String) {
  case Italy extends Country("IT")
  case UnitedKingdom extends Country("UK")
  case UnitedStates  extends Country("US")
  case Japan extends Country("JP")
}
Country.Italy.code       // IT

Generic function
  ================
def reverse[A](as: List[A]): List[A] = {
  |   as match {
  |     case Nil => Nil
    |     case head :: tail => reverse(tail) :+ head
    |   }
  | }

reverse(List("Hello", "World"))