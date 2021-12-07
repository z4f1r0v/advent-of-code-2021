package day4

import util.FileUtil

import scala.annotation.tailrec
import scala.collection.mutable

// https://adventofcode.com/2021/day/4
@main def myApp4 =
  val input = FileUtil.readInput(day = 4).split("\n\n")
  val drawnNumbers: Array[Int] = input.head.split(",").map(_.toInt)

  def parseBoard(s: String): Vector[Vector[Int]] =
    s.linesIterator.map(_.trim.split(" +").toVector.map(_.toInt)).toVector

  val boards = input.tail.map(parseBoard)

  def findWinningBoard(drawnNumbers: Array[Int], boards: Vector[Vector[Int]]): (Int, Vector[Vector[Int]]) =
    // convert all numbers to indexes
    val number2i = drawnNumbers.zipWithIndex.toMap
    // convert board of numbers to board of indexes
    val iBoard = boards.map(_.map(number2i))
    val iRows = iBoard.map(_.max)
    val iCols = iBoard.transpose.map(_.max)
    // get earliest index
    val numberIndex = iRows.min min iCols.min
    // mark all numbers earlier than chosen index as 0
    val markedBoard = boards.map(_.map(x => if (number2i(x) <= numberIndex) 0 else x))
    (numberIndex, markedBoard)

  def solution(using Ordering[Int]) =
    val (numberIndex, winningBoard) = boards.map(findWinningBoard(drawnNumbers, _)).minBy(_._1)
    val unmarkedNumbersSum = winningBoard.iterator.map(_.sum).sum
    drawnNumbers(numberIndex) * unmarkedNumbersSum

  val part1 = solution(using Ordering.Int)
  println(part1)

  val part2 = solution(using Ordering.Int.reverse)
  println(part2)