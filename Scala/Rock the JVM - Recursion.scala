package com.rockthejvm.part1basics

import scala.annotation.tailrec

object Recursion {

  def concatenate(str: String, n: Int): String = {
    @tailrec
    def concatTailrec(remainingTimes: Int, accumulator: String): String = {
      if remainingTimes <= 0 then accumulator
      else concatTailrec(remainingTimes - 1, accumulator + str)
    }
    concatTailrec(n, "")
  }

  def sumUntil(n: Int): Int = {
    @tailrec
    def sumUntilTailrec(x: Int, accumulator: Int): Int = {
      if x <= 0 then accumulator
      else sumUntilTailrec(x - 1, accumulator + x)
    }
    sumUntilTailrec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, accumulator: Int): Int = {
      if currentNumber > b then accumulator
      else sumTailrec(currentNumber + 1, currentNumber + accumulator)
    }
    sumTailrec(a, 0)
  }

  def fibonacci(n: Int): Int =
    if n == 1 || n == 2 then 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  def fib(n: Int): Int = {
    @tailrec
    def fibTailrec(i: Int, last: Int, previous: Int): Int = {
      if (i >= n) last
      else fibTailrec(i + 1, last + previous, last)
    }
    if n <= 2 then 1
    else fibTailrec(2, 1, 1)
  }

  def main(args: Array[String]): Unit = {

    println(concatenate("SCALA|", 5))
    println(sumUntil(10))
    println(sumNumbersBetween(7, 10))
    println(fibonacci(10))
    println(fib(10))
  }
}
