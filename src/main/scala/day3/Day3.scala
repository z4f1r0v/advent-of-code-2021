package day3

import util.FileUtil

import scala.collection.mutable

@main def myApp3 =
  val commands = FileUtil.getLinesIterator(adventDay = 3).toList

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

def part2(input: List[String]) = {}
