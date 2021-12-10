import scala.io.Source

object Day8 {




  def main(args: Array[String]) = {
    def allCharsIn(s1: String, s2: String): Boolean = {
      var res=true
      s1.foreach(x1=>{
        if(!s2.contains(x1)) res=false
      })
      res
    }

    val filename = "inputd8.txt"

    //val no: List[Int]=List.empty
    var count:Int=0
    var sum:Int=0
     Source.fromFile(filename).getLines.foreach(x => {

       val s = x.split("\\|")
       var freq = s(0).trim().split(" ").toList
       var freqMap=freq.map(e => ( e.length,e)).groupBy(k => k._1)
       println(freq)
       var numbers:Array[String]=Array.fill(10){"X"}

       val ONES = 2
       val FOUR = 4
       val SEVEN = 3
       val EIGHT = 7

       numbers(1)=freqMap.get(ONES).getOrElse(List.empty)(0)._2.sorted
       numbers(4)=freqMap.get(FOUR).getOrElse(List.empty)(0)._2.sorted
       numbers(7)=freqMap.get(SEVEN).getOrElse(List.empty)(0)._2.sorted
       numbers(8)=freqMap.get(EIGHT).getOrElse(List.empty)(0)._2.sorted

       numbers(3)=freqMap.get(5).get.filter(x=>allCharsIn(numbers(1).sorted,x._2.sorted)).head._2.sorted
       numbers(6)=freqMap.get(6).get.filter(x=> !allCharsIn(numbers(1).sorted,x._2.sorted)).head._2.sorted
       freq= freq.filter(x => x.sorted!=numbers(3).sorted && x.sorted!=numbers(6).sorted)
       freqMap=freq.map(e => ( e.length,e)).groupBy(k => k._1)

       numbers(5)=freqMap.get(5).get.filter(x=> allCharsIn(x._2.sorted,numbers(6).sorted)).head._2.sorted
       numbers(9)=freqMap.get(6).get.filter(x=>{println(x._2.sorted)
         println(numbers(5).sorted)
         println( x._2.sorted.contains(numbers(5).sorted))
         allCharsIn(numbers(5).sorted,x._2.sorted)}).head._2.sorted
       freq= freq.filter(x => x.sorted!=numbers(5).sorted && x.sorted!=numbers(9).sorted)
       freqMap=freq.map(e => ( e.length,e)).groupBy(k => k._1)
       numbers(2)=freqMap.get(5).get(0)._2.sorted
       numbers(0)=freqMap.get(6).get(0)._2.sorted
       //1, 4, 7, and 8
       val numbermap=(numbers zip numbers.indices).toMap

       val knownDigits: Set[Int] = Set(ONES, FOUR, SEVEN, EIGHT)
       val input = s(1).trim().split(" ").toList
       var n=0
       input.foreach(x=>{
         n=n*10+numbermap.get(x.sorted).get
       })
       println(n)
       sum=sum+n
       val inputSizeMap = input.map(e => ( e.length,e))

       val c:Int = inputSizeMap.foldLeft[Int](0) ( (sum, m) => {
         if (knownDigits.contains(m._1))
            sum + 1
         else
           sum +0

       }
       )

         //println(c)
         count=count+c
       println(count)
       println(sum)
       }


     )
  }


  }
