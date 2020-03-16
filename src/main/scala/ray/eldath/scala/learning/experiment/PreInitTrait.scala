package ray.eldath.scala.learning.experiment

trait PreInitTrait {
  val value: Int
  val division: Double = 1.0 / value
}

trait LazyTrait {
  val value: Int
  lazy val division: Double = 1.0 / value
}

object PreInit {
  def main(args: Array[String]): Unit = {
    val trait1 = new PreInitTrait {
      override val value: Int = 15
      println(s"trait1: $value $division")
    }
    println(s"trait1: ${trait1.value} ${trait1.division}")

    new LazyTrait {
      override val value: Int = 15
      println(s"traitByLazy: $value $division")
    }

    val traitByPreInitBlock = new {
      val value = 15
      //      println(s"traitByPreInitBlock: $value $division")
    } with PreInitTrait

    println(s"traitByPreInitBlock: ${traitByPreInitBlock.value} ${traitByPreInitBlock.division}")
  }
}
