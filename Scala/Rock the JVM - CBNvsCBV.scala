package com.rockthejvm.part1basics

object CBNvsCBV {

  // CBV = call by value = arguments are evaluated before function invocation
  def aFunction(arg: Int): Int = {
    println("--- starting aFunction ---")
    arg + 1
  }
  def aComputation = aFunction(23 / 1)

  // CBN = call by name = arguments are passed LITERALLY, evaluated at every reference

  def aByNameFunction(arg: => Int): Int = {
    println("--- starting aByNameFunction ---")
    arg + 1
  }

  val anotherComputation = aByNameFunction(23 / 1)

  def printTwiceByValue(x: Long): Unit = {
    println(s"By value $x")
    println(s"By value $x")
  }

  /*
       - delayed evaluation of the argument
       - argument is evaluated every time it is used
       - If the argument is not needed, it is not evaluated
   */

  def printTwiceByName(x: => Long): Unit = {
    println(s"By name $x")
    println(s"By name $x")
  }

  def infiniteLoop(): Int = 1 + infiniteLoop()
  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {

    println(aComputation)
    println(anotherComputation)

    println(printTwiceByValue(System.nanoTime()))
    println(printTwiceByName(System.nanoTime()))

    printFirst(42, infiniteLoop())

  }

}
