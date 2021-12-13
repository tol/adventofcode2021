import scala.collection.immutable.Range
import scala.io.Source

object Day11 {

  def main(args: Array[String]) = {
    val filename = "inputd11.txt"
    var octupos:Array[Array[Int]]=Array.ofDim[Int](12, 12)
    var i=0;
    Source.fromFile(filename).getLines.foreach(x => {
      var nos:Array[Int] = x.toString().toCharArray.map(x => x.asDigit).toArray
      octupos(i)=nos
      i=i+1
      println(x)

    })
    println("================")
    var firedNo=0
    for(step  <- 1 to 500){

      for( i <- 0 to 9) {
        for (j <- 0 to 9) {

          octupos(i)(j) = octupos(i)(j) + 1
        }
      }

      var fired=true;
      while (fired){
        fired=false
        for( i <- 0 to 9){
          for( j <- 0 to 9){

            if (octupos(i)(j)>9){
              firedNo=firedNo+1
              octupos(i)(j)=0
              if (i<9 && octupos(i+1)(j)!=0 ){
                octupos(i+1)(j)=octupos(i+1)(j)+1
              }
              if(j<9 && octupos(i)(j+1)!=0){
                octupos(i)(j+1)=octupos(i)(j+1)+1
              }
              if (i>0 && octupos(i-1)(j)!=0){
                octupos(i-1)(j)=octupos(i-1)(j)+1
              }
              if(j>0 &&  octupos(i)(j-1)!=0){
                octupos(i)(j-1)=octupos(i)(j-1)+1
              }
              if (i<9 && j<9 && octupos(i+1)(j+1)!=0){
                octupos(i+1)(j+1)=octupos(i+1)(j+1)+1
              }
              if (i>0 && j<9 && octupos(i-1)(j+1)!=0){
                octupos(i-1)(j+1)=octupos(i-1)(j+1)+1
              }
              if (i>0 && j>0 &&  octupos(i-1)(j-1)!=0){
                octupos(i-1)(j-1)=octupos(i-1)(j-1)+1
              }
              if (i<9 && j>0 && octupos(i+1)(j-1)!=0){
                octupos(i+1)(j-1)=octupos(i+1)(j-1)+1
              }
              fired=true
            }
          }
        }
      }



      for( i <- 0 to 9) {
        for (j <- 0 to 9) {

          print(octupos(i)(j))
        }
        println("")
      }
      println("---------------")

      var all=true
      for( i <- 0 to 9) {
        for (j <- 0 to 9) {
          if (octupos(i)(j) != 0) {
            all = false
          }
        }
    }
      if (all){
        println("xxxxxxx")
        println(step)
        //return
      }
    }
    println(octupos.toString)
    println(firedNo)
  }

}
