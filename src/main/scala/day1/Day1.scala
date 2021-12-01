package day1

import os.pwd

import scala.io.Source

@main def myApp =
  val measurements = os.read(pwd / "src" / "main" / "scala" / "day1" / "input")
    .linesIterator
    .map(_.toInt)
    .toList

  def part1(input: List[Int]) = input.sliding(2).count(s => s.head < s.last)

  println(part1(measurements))

  def part2(input: List[Int]) =
    val sums = input.sliding(3).map(_.sum)
    sums.sliding(2).count(s => s.head < s.last)

  println(part2(measurements))


