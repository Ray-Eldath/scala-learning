package ray.eldath.scala.learning.features

object AccessModel {

  class Restricted private(name: String)

  object ClassA {
    private[AccessModel] val field = "123"
  }

  class ClassB {
    val field: String = ClassA.field
  }

  def main(args: Array[String]): Unit = {
    println((new ClassB).field)
  }

  object HashRestriction {

    class Class1 {
      private val field = "123"

      def check(anotherInstance: Class1): Boolean = field == anotherInstance.field

      // error: field needs to be private but not private[this]
    }

  }

}