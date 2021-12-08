package day6

import util.FileUtil

// https://adventofcode.com/2021/day/6
@main def myApp6 =

  // count the occurrence of each timer
  def timerFrequency(input: Seq[Int]): Vector[Long] = Vector.tabulate(9)(i => input.count(_ == i))

  def stepState(state: Vector[Long]): Vector[Long] =
    val zeroCount +: stateNonZero = state
    stateNonZero.updated(6, stateNonZero(6) + zeroCount) :+ zeroCount

  def countFish(input: List[Int])(after: Int): Long =
    Iterator.iterate(timerFrequency(input))(stepState).drop(after).next().sum

  val input = FileUtil.readInput(6).split(",").map(_.toInt).toList

  val countWithInput = countFish(input)

  println(countWithInput(80))
  println(countWithInput(256))