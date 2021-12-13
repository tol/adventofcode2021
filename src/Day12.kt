import java.io.File
class Cave (val id: String, var large: Boolean,var visited:Boolean,var maxvists:Int,var visits:Int,var connections:MutableList<Cave>){
  override fun toString(): String {
    return "Cave(id='$id', large=$large, visited=$visited)"
  }
}

/**
 *
start,A,b,A,c,A,end
start,A,b,A,end
start,A,b,end
start,A,c,A,b,A,end
start,A,c,A,b,end
start,A,c,A,end
start,A,end
start,b,A,c,A,end
start,b,A,end
start,b,end

start
/   \
c--A-----b--d
\   /
end
 */
fun countPaths(node:Cave):Int{
  println(node)
  val pred=node.visits
  if (node.id=="end"){
    return 1
  }
  if ( !node.large && node.visits>node.maxvists) {
    return 0
  }else{
    var sum=0;
    node.connections.forEach{

      node.visits=pred+1
      node.maxvists=1
      sum += countPaths(it)
      node.maxvists=2
      node.visits=pred+1
      sum += countPaths(it)
     // sum += countPaths(it,false)
    }
    node.visited=false
    node.visits=pred
    return sum
  }
}
object Day12 {

  var  caves:MutableMap<String,Cave> =mutableMapOf()
  @JvmStatic
  fun main(args: Array<String>) {
    var lines: List<String> = File("inputd12.txt").readLines()
    lines = lines.sorted()
    var groups = lines.groupBy { it.split("-").get(0) }



    groups.keys.forEach {
      var cave: Cave = Cave(it, it == it.uppercase(), false,1,1, ArrayList<Cave>())
      println(it)
      caves.put(cave.id,cave)
    }

    groups.keys.forEach {
      println(it)
      groups.get(it)?.forEach{
        val sourceid=it.split("-").get(0)
        val destinationid=it.split("-").get(1)
        var des:Cave= caves.getOrElse(destinationid) { Cave(destinationid, destinationid == destinationid.uppercase(), false,1,1, mutableListOf() )}
        var source:Cave= caves.getOrElse(sourceid) { Cave(sourceid, sourceid == sourceid.uppercase(), false,1,1, mutableListOf() )}

        caves.get(sourceid)?.connections?.add(des)
        des.connections?.add(source)
        caves.put(destinationid,des)
      }


    }
    println(caves)
    println(countPaths(caves.get("start")!!))

  }

}
