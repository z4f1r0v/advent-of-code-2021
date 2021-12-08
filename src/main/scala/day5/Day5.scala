package day5

import util.FileUtil

import scala.annotation.tailrec
import scala.collection.mutable

// https://adventofcode.com/2021/day/5
@main def myApp5 =
  val input = FileUtil.getLinesList(day = 5)

  def solution(filterDiagonals: Boolean) = {
    val parseTuples = input.map { line =>
      line.split(" -> ")
        .map { s =>
          val a = s.split(",").map(_.toInt)
          (a(0), a(1))
        }
        // sort to make calculations easier
        .sorted(using Ordering.Tuple2[Int, Int].reverse)
    }
    val curatedTuples =
      if filterDiagonals
      // filter out diagonal lines
      then parseTuples.filter(a => a(0)._1 == a(1)._1 || a(0)._2 == a(1)._2)
      else parseTuples

    // for each range of tuples fill in the missing values and assign everything to a list
    curatedTuples.flatMap { a =>
      if a(0)._1 != a(1)._1 && a(0)._2 != a(1)._2
      then
        val diff = a(0)._1 - a(1)._1 + 1
        // if diagonals are this way -> \ we are adding both terms
        // 8,0 -> 0,8 = 0,8 1,7 2,6 3,5 4,4 5,3 6,2 7,1 0,8 (first + 1, second - 1)
        // 5,5 -> 8,2 = 5,5 6,4 7,3 8,2 (first + 1, second - 1)
        // if diagonals are this way -> / we are adding to the left term and subtracting from the right
        // 0,0 -> 8,8 = 0,0 1,1 2,2 3,3 4,4 5,5 6,6 7,7 8,8 (first + 1, second + 1)
        if ((a(0)._1 - a(1)._1) < 0) || ((a(0)._2 - a(1)._2) < 0)
        then List.tabulate(diff)(i => (a(1)._1 + i, a(1)._2 - i))
        else List.tabulate(diff)(i => (a(1)._1 + i, a(1)._2 + i))
      else if a(0)._1 > a(1)._1
      then
        val diff = a(0)._1 - a(1)._1 + 1
        List.tabulate(diff)(i => (a(1)._1 + i, a(1)._2))
      else
        val diff = a(0)._2 - a(1)._2 + 1
        List.tabulate(diff)(i => (a(1)._1, a(1)._2 + i))
    }
      // group repetitions by overlap and filter overlaps above 1
      .groupBy(identity).view.mapValues(_.size).count(_._2 > 1)
  }


  val part1 = solution(filterDiagonals = true)
  println(part1)

  val part2 = solution(filterDiagonals = false)
  println(part2)
