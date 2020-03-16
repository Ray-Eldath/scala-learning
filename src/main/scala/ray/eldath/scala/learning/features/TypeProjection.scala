package ray.eldath.scala.learning.features

trait TypeHandler {
  type Type
}

class IntType extends TypeHandler {
  override type Type = Int
}

class StringType extends TypeHandler {
  override type Type = String
}

object TypeProjection {
  def main(args: Array[String]): Unit = {
    val intType: IntType#Type = 1
    val stringType: StringType#Type = "str"
    // it's easy to find that type projection herein is very similar to syntax of type lambda

    //    val uncompilableProjection: TypeHandler#Type = 1
    println(intType)
    println(stringType)
  }
}
