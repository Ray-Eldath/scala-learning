package ray.eldath.scala.learning.features

class LazyVal {
  val abc = "abc"
  lazy val abcLazy = "abc"
  lazy val abcLazyBlock: String = {
    "abc"
  }
}
