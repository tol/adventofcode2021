import scala.::
import scala.io.Source
import scala.util.control.Breaks.break

object Day4 {
   class Board( boardx:Array[Array[Int]]){
    private var  marked:Array[Array[Boolean]]=Array.ofDim[Boolean](5,5)
    def markValue(x:Int): Unit ={
      for( i <- 0 to 4){
        for( j <- 0 to 4){
        if (boardx(i)(j)==x){
          println(i)
          marked(i)(j)=true
        }
        }
      }
    }

     def bingo(): Boolean ={
       for( i <- 0 to 4){
         var br:Boolean=true
         var bc:Boolean=true
         for( j <- 0 to 4){
             br=br && marked(i)(j)
             bc=bc && marked(j)(i)
           }
         if (br) return true;
         if (bc) return true;

         }
       return false
       }

     def sumUnmarked(): Int ={
       var sum=0
       for( i <- 0 to 4){
         for( j <- 0 to 4){
           if (! marked(i)(j)){
             sum+=boardx(i)(j)

           }
         }
       }
       return sum
     }

  }

  def main(args: Array[String]) = {

    val filename = "inputdd4.txt"

    //val no: List[Int]=List.empty
    var lines = Source.fromFile(filename).getLines.toList

    val nos = lines(0).toString().split(",").map(x => x.toInt)
    println(nos(1))

    var boards:List[Board]=List.empty[Board]

    /**
     *
    57 80 91 40 12
62 36 72  0 20
55 60 25 92 96
14  2 17 18 86
 1  4 90 66 38
     */
    var tempBoard:Array[Array[Int]]=Array.ofDim[Int](5,5)
    lines=lines.filter(p => !p.isEmpty).map(x=>x.trim)
    for (i <- 1 to lines.size-1){
     println(lines(i))
     val row: Array[Int]=lines(i).toString().split("\\s+").map(x => x.toInt).toArray
      tempBoard((i-1)%5)=row
      if ((i-1) %5==4){
        val board:Board=new Board(tempBoard)
        boards=boards :+ board
        tempBoard=Array.ofDim[Int](5,5)
      }
    }

   var  lastBoard:Board=null
    var lastN:Int =0
    nos.foreach(n => {
      boards.foreach(b=> b.markValue(n))
      if (boards.filter(b=> b.bingo()).size>0){
        //println(n*boards.filter(b=> b.bingo())(0).sumUnmarked())
        lastBoard=boards.filter(b=> b.bingo())(0)
        lastN=n
      }
      boards=boards.filter(b => !b.bingo())
    })

    println(lastN*lastBoard.sumUnmarked())
    /*for (line <- Source.fromFile(filename).getLines) {
     println(line)
    }*/
  }

}
