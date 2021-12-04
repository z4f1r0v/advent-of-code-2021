package util

import os.pwd

object FileUtil {
  def getLinesIterator(adventDay: Int): Iterator[String] =
    os.read(pwd / "src" / "main" / "scala" / s"day${adventDay}" / "input")
      .linesIterator
    
  def getLinesList(adventDay: Int): List[String] = getLinesIterator(adventDay).toList
}
