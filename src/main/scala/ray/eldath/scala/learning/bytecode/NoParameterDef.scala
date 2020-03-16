package ray.eldath.scala.learning.bytecode

class NoParameterDef {
  def test(): Unit = {}

  def test1: String = "123"
}

object NoParameterDef {
  def main(args: Array[String]): Unit = {
    val i = new NoParameterDef()
    i.test1
    i.test()
  }
}