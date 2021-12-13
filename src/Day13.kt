import java.io.File

object Day13{

  fun foldUp (y:Int,points:MutableSet<Pair<Int, Int>>):MutableSet<Pair<Int, Int>> {

    return mutableSetOf<Pair<Int, Int>>().apply {addAll(points.filter { p-> p.second >y }.map { p -> Pair(p.first,y+y-p.second) }+ points.filter { p-> p.second <= y })}
  }
  fun foldleft (x:Int,points:MutableSet<Pair<Int, Int>>):MutableSet<Pair<Int, Int>> {

    return mutableSetOf<Pair<Int, Int>>().apply {addAll(points.filter { p-> p.first >x }.map { p -> Pair(x+x-p.first,p.second) }+ points.filter { p-> p.first <= x })}
  }

  @JvmStatic
  fun main(args: Array<String>) {
    var input: List<String> = File("inputd13.txt").readLines()

    var points = mutableSetOf<Pair<Int, Int>>().apply {
        addAll(input.filter{x -> x.contains(",")}.map {
          val s=it.split(",")
          Pair(s[0].toInt(), s[1].toInt())
        })
      }

    input.filter { x -> x.contains("fold") }.forEach {
      //fold along y=7
      var s=it.split(" ")[2].split("=")
      if (s[0]=="y")
        points=foldUp(s[1].toInt(),points)
      else
        points=foldleft(s[1].toInt(),points)
      println(points.size)
    }
   val maxy= points.maxOf {p->p.first  }
    val maxx= points.maxOf {p->p.second  }
    for (i in 0..maxx+2) {
      for (j in 0..maxy+2) {
        if (Pair(j, i) in points)
          print("#")
        else
          print(".")
      }
      println(" ")
    }
    println(points)
  }
}
