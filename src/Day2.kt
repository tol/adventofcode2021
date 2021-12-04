import java.io.File

object Day2 {

  @JvmStatic
  fun main(args: Array<String>) {
    var h=0
    var v=0
    var aim=0
    File("inputd2.txt").forEachLine {
      val pattern1 = Regex("(forward) (\\d+)")
      val pattern2 = Regex("(down) (\\d+)")
      val ans : MatchResult? = pattern1.matchEntire(it)
      if (ans!=null) {
        val x=it.substring("forward ".length)
        h+=x.toInt()
       // v+=aim*x.toInt()
      }else{
        val ans : MatchResult? = pattern2.matchEntire(it)
        if (ans!=null) {
          val x=it.substring("down ".length)
          v+=x.toInt()
          //aim+=x.toInt()
        } else{
          val x=it.substring("up ".length)
          v-=x.toInt()
          //aim-=x.toInt()
        }

      }
      println(it)
    }


    println(h)
    println(v)
    println(h*v)
  }

}
