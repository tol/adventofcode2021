import java.io.File
import java.lang.Math.abs

object Day5 {
  @JvmStatic
  fun main(args: Array<String>) {
    //645,570 -> 517,570
    var  obstacles:ArrayList<Array<Int>> = ArrayList()
    File("inputd5.txt").forEachLine {
      val regex = Regex("\\d+")
      val resultList :Array<Int> = regex.findAll(it).map { it.value.toInt() }.toList().toTypedArray()
      obstacles.add(resultList)
      //println(resultList)
    }
    val max: Int = obstacles.flatMap { it.asList() }.maxOrNull()?.toInt() ?: 0

    var matrix : Array<Array<Int>> =   Array(max+1) {Array(max+1) {0} }

    //obstacles= obstacles.filter { it -> it[0]==it[2] || it[1]==it[3] } as ArrayList<Array<Int>>

    obstacles.forEach {
      if (it[0]==it[2]){
        for (i in it[1]..it[3]) {
          matrix[it[0]][i]=matrix[it[0]][i]+1
        }
        for (i in it[3]..it[1]) {
          matrix[it[0]][i]=matrix[it[0]][i]+1
        }
      }
      if (it[1]==it[3]){
        for (i in it[0]..it[2]) {
          matrix[i][it[1]]=matrix[i][it[1]]+1
        }
        for (i in it[2]..it[0]) {
          matrix[i][it[1]]=matrix[i][it[1]]+1
        }
      }
      if  ( abs(it[1]-it[3]) == abs(it[0]-it[2])){
        var x=it[0]
        var y=it[1]
        while (x!=it[2]){
          matrix[x][y]=matrix[x][y]+1
          x += (it[2] - it[0]) / abs(it[0] - it[2])
          y+=(it[3] - it[1]) / abs(it[1] - it[3])
        }
        matrix[it[2]][it[3]]= matrix[it[2]][it[3]]+1

      }

    }
    println(matrix.flatMap {  it.asList()}.filter { x-> x>=2 }.size)
  }
}
