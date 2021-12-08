package day7

import util.FileUtil

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// https://adventofcode.com/2021/day/7
@main def myApp7 =
  val input = FileUtil.readInput(day = 6).split(",").map(_.toInt).toList
//  val mostCommonPosition = input.groupBy(identity).view.mapValues(_.size).head._1
  val mostCommonPosition = input.sum / input.length
  val totalCost = input.map { p =>
    val diff = p - mostCommonPosition
    if diff < 0
    then diff * (-1)
    else diff
  }.sum

  println(totalCost)