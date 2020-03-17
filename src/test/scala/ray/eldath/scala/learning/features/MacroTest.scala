package ray.eldath.scala.learning.features

import ray.eldath.scala.learning.features.Macro.fixed

case class Vary(var value: Int = 10)

object TestModification {
  def main(args: Array[String]): Unit = {
    val vary = Vary()

    fixed(vary.value == 10) {
      println(vary.value + 5)
      vary.value += 10 // failed
    }
  }
}

object TestAssertion {

  def main(args: Array[String]): Unit = {
    val vary = Vary()

    fixed(vary.value == 15) {} // failed
  }
}
