import java.io.File

object Day16 {
  @JvmStatic
  fun main(args: Array<String>) {
    var input: List<String> = File("inputd16test.txt").readLines()

    val binary: Map<String, String> = mapOf(
      "0" to "0000",
      "1" to "0001",
      "2" to "0010",
      "3" to "0011",
      "4" to "0100",
      "5" to "0101",
      "6" to "0110",
      "7" to "0111",
      "8" to "1000",
      "9" to "1001",
      "A" to "1010",
      "B" to "1011",
      "C" to "1100",
      "D" to "1101",
      "E" to "1110",
      "F" to "1111"
    )
    var binarylist: String =
      input[0].toCharArray().map({ x -> binary.get(x.toString()) }).foldRight(" ") { acc, y ->  acc+y}

    println(binarylist)
    var  sumv=0

    while (binarylist.length>0) {
      var v = binarylist.take(3)
      println(v.toInt(radix = 2))
      sumv=sumv+v.toInt(radix = 2)

      binarylist = binarylist.slice(3..binarylist.length - 1)
      var packetType = binarylist.take(3)
      binarylist = binarylist.slice(3..binarylist.length - 1)
      println(packetType.toInt(2))
      var pInt: Int = packetType.toInt()
      when (pInt) {
        4 -> {
          // type: 4 - literal   1(1not teh last , 0 last )+ 4 bits packet: 0111
          var subpacket = binarylist.take(4)
          binarylist = binarylist.slice(4..binarylist.length - 1)
          while (subpacket[0] != '0') {
            subpacket = binarylist.take(4)
            binarylist = binarylist.slice(4..binarylist.length - 1)
          }
        }
        else -> {
          var lBit = binarylist.take(1)
          binarylist = binarylist.slice(1..binarylist.length - 1)
          when (lBit) {
            "0" -> {
              var length = binarylist.take(15).toInt(radix = 2)
              binarylist = binarylist.slice(15..binarylist.length - 1)
              //1 leebgth bit: 0 + 15 bits the total length of the next packages
              binarylist = binarylist.slice(length..binarylist.length - 1)
            }
            else -> {

              var nrp = binarylist.take(11).toInt(radix = 2)
              binarylist = binarylist.slice(11..binarylist.length - 1)

              for (i in 0..nrp) {
                binarylist =
                  binarylist.slice(11..binarylist.length - 1)
              }
            }
          }
        }
      }
    }

    System.out.println(sumv)
    //3 bits =veresion
    // 3 bits packet tyoe id :

    //other operator packet: 1 leebgth bit: 0 + 15 bits the total length of the next packages
    // 1+ 11 bits- numbere of subpackets
  }

}
