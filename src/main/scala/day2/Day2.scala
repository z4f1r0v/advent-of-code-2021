package day2

import util.FileUtil

@main def myApp =
  val commands = FileUtil.getLinesIterator(adventDay = 2).toList

  def part1(input: List[String]) =
    input.foldLeft(Position()) { (p, c) =>
      val commandValue = c.split(" ")
      val command = commandValue(0)
      val value = commandValue(1).toInt
      command match {
        case "forward" => p.copy(horizontal = p.horizontal + value)
        case "down" => p.copy(depth = p.depth + value)
        case "up" => p.copy(depth = p.depth - value)
      }
    }.product

  println(part1(commands))

private case class Position(horizontal: Int = 0, depth: Int = 0) {
  def product: Int = horizontal * depth
}