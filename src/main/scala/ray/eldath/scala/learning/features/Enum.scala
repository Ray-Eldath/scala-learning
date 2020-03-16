package ray.eldath.scala.learning.features

object Enum extends Enumeration {
  val A = "A"
  val B = "B"
}

object AnotherEnum extends Enumeration {
  type AnotherEnum = Value

  val A = Value("A")
}

object Test {

  def main(args: Array[String]): Unit = {
    val gross = 100000F
    val net = 3542F
    val percent = (net / gross) * 100

    println(f"$$$gross%.2f  vs.  $$$net%.2f  or $percent%.1f%%")

    val d = 100.22
    String.format("%2d", d)

    //    println(f"$d%2d") compile-time validation: better than Java's format
  }
}