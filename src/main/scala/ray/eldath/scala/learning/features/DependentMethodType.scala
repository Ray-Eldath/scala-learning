package ray.eldath.scala.learning.features

trait Type {
  type T

  def value: T
}

case class TypeA(value: String = "typeA") extends Type {
  override type T = String
}

case class TypeB(value: Int = 1) extends Type {
  override type T = Int
}

object DependentMethodType {
  def handle(t: Type): t.T = t.value // dependent method type here! (t.T)
  // this is called magnet design pattern

  def main(args: Array[String]): Unit = {
    println(handle(TypeA()))
    println(handle(TypeB()))
  }
}
