package ray.eldath.scala.learning.fp

object Trampoline {
  def isEven(l: List[Int]): Boolean = if (l.isEmpty) true else isOdd(l.tail)

  def isOdd(l: List[Int]): Boolean = if (l.isEmpty) false else isEven(l.tail)

  import ray.eldath.scala.learning.features.Pipeline._

  def main(args: Array[String]): Unit = {
    (1 to 10).toList |> isEven |> println
    (1 to 100_000_000).toList |> isOdd |> println
  }
}
