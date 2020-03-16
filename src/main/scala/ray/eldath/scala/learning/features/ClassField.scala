package ray.eldath.scala.learning.features

case class ClassField(var field: String) {
  var value = "123"
}

object ClassField {
  def main(args: Array[String]): Unit = {
    ClassField("456").field = "567"
  }
}
