package day1

import os.pwd

import scala.io.Source

@main def myApp =
  val input = os.read(pwd / "src" / "main" / "scala" / "day1" / "input")
    .split("\n")

  val part1 =
    input.sliding(2)
      .foldLeft(0) { (acc, as) =>
        if as(0).toInt < as(1).toInt
        then acc + 1
        else acc
      }

  println(part1)

  val part2 = input.sliding(3)
    .sliding(2)
    .foldLeft(0) { (acc, as) =>
      def sumElements(group: Array[String]): Int = group.map(_.toInt).sum

      //noinspection ZeroIndexToHead
      if sumElements(as(0)) < sumElements(as(1))
      then acc + 1
      else acc
    }

  println(part2)


