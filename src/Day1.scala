import scala.::
import scala.io.Source

object Day1 {
  def mavg(values: List[Int], period: Int): List[Int] =
    values.inits    //shrinking list of inits
      .toList   //result type
      .reverse  //growing list of inits
      .tail     //drop the empty one
      .map(_.takeRight(period).sum) //sum the window

  def main(args: Array[String]) = {
    println("Hello, world")
    val filename = "input.txt"
    var count = 0
    var pred = Int.MaxValue
    var values = List.empty[Int]
    for (line <- Source.fromFile(filename).getLines) {
      count = if (line.toInt > pred) count + 1 else count
      pred = line.toInt
      values=values :+  line.toInt
    }

    var count2 = 0
    var pred2 = Int.MaxValue
    var sums = mavg(values, 3)
    sums=sums.drop(2)
    for (line <- sums) {
      count2 = if (line > pred2) count2 + 1 else count2
      pred2 = line

    }
    println(count2)
  }
}
