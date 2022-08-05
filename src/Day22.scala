import scala.io.Source
import scala.math.abs

object Day22 {

  class Area( x1:Int,x2:Int,y1:Int,y2:Int,z1:Int,z2:Int){

    def volume(): Int = {
      return abs(x1 - x2) * abs(y1 - y2) * abs(z1 - z2);
    }
    def split(a:Area): List[Area] = {
      return abs(x1 - x2) * abs(y1 - y2) * abs(z1 - z2);
    }
  }

  def main(args: Array[String]) = {
    val filename = "inputd22.txt"
   // on x=-31..18,y=-8..36,z=-8..37
    //off x=-23..-11,y=-17..0,z=-40..-26

   var  onares:List[Area]=List.empty

    val input = Source.fromFile(filename).getLines().toList
  }
}
