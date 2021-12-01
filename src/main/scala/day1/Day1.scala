package day1

import os.pwd

import scala.io.Source

@main def myApp =
  val result = os.read(pwd / "src" / "main" / "scala" / "day1" / "input")
    .split("\n")
    .sliding(2)
    .foldLeft(0) { (acc, as) =>
      if as(0).toInt < as(1).toInt
      then acc + 1
      else acc
    }
  println(result)


