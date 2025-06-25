// https://alvinalexander.com/scala/

// Matchable

def isTrue(a: Matchable): Boolean = a match
case 0 | "0" | "" | false => false
case _ => true


// Imports

import com.alvinalexander.StringUtils.*   // Scala 3
import com.alvinalexander.StringUtils._   // Scala 2

// Tuples

val tuple = (1, true, "foo")

tuple._2    // true -- Scala 2
tuple(1)    // true -- Scala 3

// intersection type

trait Animal
trait HasTail

case class Dog(name: String) extends Animal, HasTail
case class Cat(name: String) extends Animal, HasTail

val xs = Vector(Dog(Hugo), Cat(Oscar))   // Vector[Animal & HasTail]

// read an element from a vector

xs(0) // Animal & HasTail = Dog(Hugo)

// vector

val names = Vector("oscar", "hugo", "george")

for name <- names do println(name)

val result = for name <- names yield name.capitalize     // Vector(Oscar, Hugo, George)

// timer

def timer[A](blockOfCode: => A) =
val startTime = System.nanoTime
val result = blockOfCode
val stopTime = System.nanoTime
val delta = stopTime - startTime
(result, delta / 1_000_000d)

// a Buffer is a growable and shrinkable Seq
//
// ArrayBuffer is an indexed sequential collection
// ListBuffer is a linear sequential collection

import scala.collection.mutable.ArrayBuffer

// empty ArrayBuffer
val ys = new ArrayBuffer[Int]()

// ArrayBuffer with 100000 slots
val xs = new ArrayBuffer[Int](100_000)

// ArrayBuffer with given elements
val zs = ArrayBuffer(1,2,3,4,5)

val arrayBuffer = (1 to 10).toBuffer

ArrayBuffer.fill(3)("foo")            // ArrayBuffer(foo, foo, foo)

ArrayBuffer.tabulate(4)(n => n + 1)   // ArrayBuffer(1, 2, 3, 4)

// enum

enum CrustSize:
case Small, Medium, Large

enum CrustType:
case Thin, Thick, Regular

enum Topping:
case Cheese, Mushrooms, GreenPeppers, Olives

import CrustSize.*
import CrustType.*
import Topping.*

case class Pizza(
                  crustSize: CrustSize,
                  crustType: CrustType,
                  topping: Seq[Topping]
                )

val pizza = Pizza(Large, Thin, Seq(GreenPeppers, Cheese))

def getSize(p: Pizza): CrustSize = p.crustSize

getSize(pizza)        // Large

// enum with parameter

enum HttpResponse(val code: Int):
case Ok extends HttpResponse(200)
case MovedPermanently extends HttpResponse(301)
case InternalServerError extends HttpResponse(500)

import HttpResponse.*

println(Ok.code)

// User-defined members of enums

enum Planet(mass: Double, radius: Double):
private final val G = 6.67300E-11
def surfaceGravity = G * mass / (radius * radius)
def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity
case Mercury extends Planet(3.303e+23, 2.4397e6)
case Venus   extends Planet(4.869e+24, 6.0518e6)
case Earth   extends Planet(5.976e+24, 6.37814e6)
case Mars    extends Planet(6.421e+23, 3.3972e6)
case Jupiter extends Planet(1.9e+27,   7.1492e7)
case Saturn  extends Planet(5.688e+26, 6.0268e7)
case Uranus  extends Planet(8.686e+25, 2.5559e7)
case Neptune extends Planet(1.024e+26, 2.4746e7)
end Planet

import Planet.*

println(Mars.surfaceGravity)

// Scala 3 - class definition

class Employee(var firstName: String, var lastName: String):
def fullName: String = s"$firstName $lastName"
end Employee


// OOP

import scala.collection.mutable.ArrayBuffer

enum CrustSize:
case Small, Medium, Large

enum CrustType:
case Thin, Thick, Regular

enum Topping:
case Cheese, Mushrooms, GreenPeppers, Olives

import CrustSize.*
import CrustType.*
import Topping.*


class Pizza(
             var crustSize: CrustSize = Medium,
             var crustType: CrustType = Regular
           ):

// a private field
private val toppings = ArrayBuffer[Topping]()

// members inside the class
def addTopping(t: Topping): Unit =
  toppings += t

def addToppings(ts: Topping*): Unit =
  toppings.appendAll(ts)

def removeAllToppings(): Unit =
  toppings.clear()


override def toString = s"""
                           |A $crustSize pizza with a $crustType crust.
                           |Toppings:     ${this.toppings.mkString(", ")}""".stripMargin


end Pizza

val p = Pizza()

p.crustSize = Large
p.crustType = Thin

p.addTopping(Olives)
p.addTopping(Cheese)

println(p)

// A Large pizza with a Thin crust.
// Toppings:     Olives, Cheese

// Setter

class Employee(var firstName: String, var lastName: String):
private var _salary = 0d
def salary: Double = _salary
// this is the new setter method:
def salary_=(newSalary: Double): Unit =
  _salary = newSalary
def fullName: String = s"$firstName $lastName"
end Employee

val e = Employee("Boris", "Blank")

println(e.salary)           // 0.0

e.salary = 500

println(e.salary)           // 500.0

// Traits - Using Traits As Interfaces

trait HasTail:
def startTail(): Unit
def stopTail(): Unit

trait CanSpeak:
def speak(): Unit

class Dog extends HasTail, CanSpeak:
def speak(): Unit = println("woof")
def startTail(): Unit = println("Tail is started")
def stopTail(): Unit = println("Tail is stopped")

class Cat extends HasTail, CanSpeak:
def speak(): Unit = println("miau")
def startTail(): Unit = println("Tail is started")
def stopTail(): Unit = println("Tail is stopped")

def saySomething(speaker: CanSpeak): Unit = speaker.speak()


val dog = Dog()
val cat = Cat()

dog.startTail()
dog.stopTail()

saySomething(dog)
saySomething(cat)