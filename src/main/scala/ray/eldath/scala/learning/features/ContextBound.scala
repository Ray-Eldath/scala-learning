package ray.eldath.scala.learning.features

object Serialization {

  case class Output[T](value: T) {
    def out = s" --$value-- "
  }

  type Writable[T] = T => Output[T]

  implicit val fromInt: Writable[Int] = i => Output(i)
  implicit val fromFloat: Writable[Float] = f => Output(f)
  implicit val fromString: Writable[String] = s => Output(s)
}

object Printer {

  import ray.eldath.scala.learning.features.Serialization._

  def printValue[T: Writable](t: T): Unit = println(t.out)

  def printValue2[T](t: T)(implicit tt: Writable[T]): Unit = println(tt(t).out)

  def main(args: Array[String]): Unit = {
    printValue(50)
    printValue2(50)

    printValue(1.0f)
    printValue2(1.0f)

    printValue("abc")
    printValue2("abc")
    //    printValue(5.0d)
  }
}