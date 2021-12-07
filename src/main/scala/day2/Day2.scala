package day2

import day2.Position
import util.FileUtil

// https://adventofcode.com/2021/day/2
@main def myApp2 =
  val commands = FileUtil.getLinesList(day = 2)

  def submarineNavigation(input: List[String])(commandInterpreter: (Position, String, Int) => Position): Int = {
    input.foldLeft(Position()) {
      (p, c) =>
        val commandUnit = c.split(" ")
        val command = commandUnit(0)
        val unit = commandUnit(1).toInt
        commandInterpreter(p, command, unit)
    }.product
  }

  val submarineNavigationWithInput = submarineNavigation(commands)

  def part1(input: List[String]) =
    submarineNavigationWithInput(
      (p, command, value) => command match {
        case "forward" => p.copy(horizontal = p.horizontal + value)
        case "down" => p.copy(depth = p.depth + value)
        case "up" => p.copy(depth = p.depth - value)
      }
    )

  println(part1(commands))

  def part2(input: List[String]) =
    submarineNavigationWithInput(
      (p, command, unit) => command match {
        case "forward" => p.copy(horizontal = p.horizontal + unit, depth = p.depth + p.aim * unit)
        case "down" => p.copy(aim = p.aim + unit)
        case "up" => p.copy(aim = p.aim - unit)
      }
    )

  println(part2(commands))

private case class Position(horizontal: Int = 0, depth: Int = 0, aim: Int = 0) {
  def product: Int = horizontal * depth
}