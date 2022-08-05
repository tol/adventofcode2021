import scala.io.Source

object Day14 {

  def main(args: Array[String]) = {
    val filename = "inputd14.txt"

    val input=Source.fromFile(filename).getLines().toList

    var s = input(0)
    //CH -> B
    val pairs=input.slice(2,input.size).map(x =>{
      val t=x.split(" -> ")
     (t(0),t(1))
    }).toMap

    for (j <- 1 to 40)
    {
      var t = ""
      for (i <- 0 to s.length - 2) {
       // println(pairs.get(s.slice(i, i + 2)))
        val k=pairs.get(s.slice(i, i + 2))
        if (k.get!="") {
          t = t + s(i) + k.get
        } else {
          t = t + s(i)
        }
      }
      t = t + s(s.length-1)
      println(t.length)
      s = t
    }

    println( s.groupBy(c =>c ).map(p => p._2.length).max-  s.groupBy(c =>c ).map(p => p._2.length).min)
 //s.groupBy(c =>c )


   /// println(pairs)
  }
}

/**
 * After step 1: NCNBCHB
 *               NCNBCHB
After step 2: NBCCNBBBCBHCB
              NBCCNBBBCBHCB
After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
              NBBBCNCCNBBNBNBBCHBHHBCHB
After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
              NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
 */
