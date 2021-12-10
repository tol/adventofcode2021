import scala.io.Source

object Day6 {

  def main(args: Array[String]) = {

    val filename = "inputd6.txt"

    //val no: List[Int]=List.empty
    var lines = Source.fromFile(filename).getLines.toList

    var nos:Array[Int] = lines(0).toString().split(",").map(x => x.toInt).toArray

    for(i <- 1 to 256){
      var newnos=Array.empty[Int]
      println(i)
      println(nos.size)
      for(j <- 0 to nos.length-1) {

        if (nos(j) == 0) {

          newnos=newnos :+ 8
          nos(j)=6
        }else{
          nos(j)= (nos(j)-1)
        }
        //nos=List.empty[Int]
       // nos=Array(newnos: _*)

        //nos=List(newnos:_*)
      }
      nos=nos.appendedAll(newnos)
      }

    println(nos.size)

    }
  }

