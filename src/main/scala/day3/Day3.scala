package day3

import util.FileUtil

import scala.annotation.tailrec
import scala.collection.mutable

@main def myApp3 =
  val commands = FileUtil.getLinesList(adventDay = 3)

    def part1(input: List[String]) =
      val zerosPerColumn = mutable.Map.from(List.fill(input.head.length)(0).zipWithIndex.map(_.swap))

      input.foreach(bn =>
        bn.zipWithIndex.foreach { case (c, i) =>
          if c == '0'
          then zerosPerColumn(i) += 1
        }
      )

      val mostCommonBinary = zerosPerColumn.foldLeft("") { case (acc, (k, v)) =>
        if v > (input.length / 2)
        then acc + "0"
        else acc + "1"
      }
      val gammaRate = Integer.parseInt(mostCommonBinary, 2)

      // bitwise not is giving 32 bit integer back so we need to pick only last 12 chars
      // and convert back to integer
      val leastCommonBinary = (~gammaRate).toBinaryString.takeRight(input.head.length)
      val epsilonRate = Integer.parseInt(leastCommonBinary, 2)

      gammaRate * epsilonRate

    println(part1(commands))

  def part2(input: List[String]) =
    @tailrec
    def ratingCalculator(currentPosition: Int, input: List[String])(bitFilter: (Char, Char) => Boolean): String =
      if input.size == 1
      then input.head
      else
        val colZeros = input.foldLeft(0) { (acc, n) =>
          if n(currentPosition) == '0'
          then acc + 1
          else acc
        }

        val commonBit =
          if colZeros > (input.length / 2)
          then '0'
          else '1'

        val filteredInput = input.filter(s => bitFilter(s(currentPosition), commonBit))
        ratingCalculator(currentPosition + 1, filteredInput)(bitFilter)

    val defaultRatingCalculator = ratingCalculator(0, input)
    val oxygenGeneratorRating = defaultRatingCalculator(_ == _)
    val cO2ScrubberRating = defaultRatingCalculator(_ != _)

    List(oxygenGeneratorRating, cO2ScrubberRating).map(Integer.parseInt(_, 2)).product

  println(part2(commands))
