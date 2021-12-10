import java.io.File
import java.lang.Math.abs
import kotlin.math.ceil

object  Day7 {

  @JvmStatic
  fun main(args: Array<String>) {
   File("inputd7.txt").forEachLine {
       var  list=it.split(",").map { x-> x.toInt() }
       val sorted=list.sorted()
       var median=0
        if (sorted.size %2==0){
          median=(sorted.get(sorted.size/2)+sorted.get(sorted.size/2-1))/2
          println((sorted.get(sorted.size/2)+sorted.get(sorted.size/2-1))/2)
        }else {
          median=sorted.get(sorted.size/2)
          println(sorted.get(sorted.size/2))
        }

       val fuel= sorted.foldRight(0,{op,acc-> abs(median-op)+acc})
       println(fuel)

       val average= ceil((sorted.sum().toDouble() / sorted.size.toDouble())  ).toInt();
       println(average)
        var min=Int.MAX_VALUE
       for(i in 1..average) {

         val fuel2 = sorted.foldRight(0,
           { op, acc -> abs(i - op) * (abs(i - op) + 1) / 2 + acc })
         if (fuel2<min){
           min=fuel2
         }
         println(fuel2)
       }
       println(min)
       //println(it)

      }
    }
  }
