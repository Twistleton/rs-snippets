package com.rockthejvm.part1basics

import scala.annotation.tailrec

// Types

// Short  - 2 bytes - 16 bits
// Int    - 4 bytes - 32 bits
// Long   - 8 bytes - 64 bits
// Float  - 4 bytes - 32 bits
// Double - 8 bytes - 64 bits

def stringConcatenation(str: String, n: Int): String =
  if n == 0 then ""
  else if n == 1 then str
  else str + stringConcatenation(str, n-1)

def voidFunction(): Unit = ()

def greeting(name: String, age: Int): String =
  s"Hi my name is $name and I am $age years old."

def factorial(n: Int): Int =
  if n < 0 then 0
  else if n == 0 then 1
  else n * factorial(n - 1)

def fibonacci(n: Int): Int =
  if n == 1 || n == 2 then 1
  else fibonacci(n-1) + fibonacci(n-2)

def isPrime(n: Int): Boolean = {
  @tailrec
  def isPrimeUntil(d: Int): Boolean = {
    if d <= 1 then true
    else if n % d == 0 then false
    else isPrimeUntil(d-1)
  }
  if n == 1 then false else isPrimeUntil(n / 2)
}

// Rock the JVM-Solution
def isPrimeSolution(n: Int): Boolean = {
  @tailrec
  def isPrimeUntil(t: Int): Boolean =
    if t <= 1 then true
    else n % t != 0 && isPrimeUntil(t -1)
  isPrimeUntil(n / 2)
}

object Functions {
  def main(args: Array[String]): Unit = {

    def aNoArgFunction(): Int = 45
    def aParameterlessFunction: Int = 45

    println(stringConcatenation("Hugo", 3))
    println(greeting("Hugo", 9))

    val q1 = if (true) "YES" else "NO"         // Scala 2-style
    val q2 = if true then "YES" else "NO"      // Scala 3-style with the keyword then

    println(q1)
    println(q2)

    println(factorial(5))
    println(fibonacci(10))

    println("is    1 a prime number " + isPrime(1))
    println("is   97 a prime number " + isPrime(97))
    println("is 1008 a prime number " + isPrime(1008))
    println("is 1009 a prime number " + isPrime(1009))

    println("-----------------------------------------")

    println("is    1 a prime number " + isPrimeSolution(1))
    println("is   97 a prime number " + isPrimeSolution(97))
    println("is 1008 a prime number " + isPrimeSolution(1008))
    println("is 1009 a prime number " + isPrimeSolution(1009))

  }
}