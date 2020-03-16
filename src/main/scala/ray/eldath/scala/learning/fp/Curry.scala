package ray.eldath.scala.learning.fp

object Curry {
  def fun(s1: String, s2: String): String = s1 + s2

  def partialApplied(s1: String)(s2: String): String = fun(s1, s2)

  def curried(s1: String): String => String = s2 => s1 + s2

  def curriedByStd(s1: String): String => String = (fun _).curried(s1)

  import ray.eldath.scala.learning.features.Pipeline._

  def main(args: Array[String]): Unit = {
    val a: (String, String) => String = fun _
    //    val b: PartialFunction[(String, String), String] = fun _ | type mismatch: found (String, String) => String

    val hello1: String => String = partialApplied("Hello")
    val hello2: String => String = curried("Hello")

    hello1(" World!") |> println
    hello2(" World!") |> println
  }

}
