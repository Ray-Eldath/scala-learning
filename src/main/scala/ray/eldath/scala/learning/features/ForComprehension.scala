package ray.eldath.scala.learning.features

object ForComprehension {

  import Pipeline._

  def main(args: Array[String]): Unit = {
    val names = List("Eay Eldath", "ice1000", "himself65", "phosphorous15", "LemonHX")

    (for {
      s <- names
      c <- s
      if c != ' '
      uc = c.toUpper
    } yield s"$c-$uc") |> println
  }
}
