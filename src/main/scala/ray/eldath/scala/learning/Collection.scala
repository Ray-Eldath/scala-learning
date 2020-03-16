package ray.eldath.scala.learning

object Collection {
  def main(args: Array[String]): Unit = {
    for (i <- Seq(1, 3, 5, 2)) {
      i match {
        case _ if i % 2 == 0 => println(s"even $i")
        case _ => println(s"odd $i")
      }
    }
  }
}
