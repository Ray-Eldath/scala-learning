package ray.eldath.scala.learning.bytecode

class ByNameParameter {
  def compute(int: => Int): Unit = println(int)
}
