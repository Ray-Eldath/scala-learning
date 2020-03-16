package ray.eldath.scala.learning.experiment

case class BadInherit(field: String)

class BadInheritChild(field: String, val childField: String) extends BadInherit(field)

object BadInheritChild {
  def apply(field: String, childField: String): BadInheritChild = new BadInheritChild(field, childField)
}

object BadInherit {
  def main(args: Array[String]): Unit = {
    val list = List(BadInheritChild("ray", "eldath"),
      BadInheritChild("phosphorous", "15"),
      BadInheritChild("ice", "1000"),
      BadInheritChild("ice", "1k"))
    // problem here: hashCode from Parent will be called, and the last two lines will
    // have the same hashcode even their fields are not equal!
    // lesson: DO NOT USE INHERITANCE TO ADD STATE!

    list foreach (e => println(e.hashCode()))
  }
}
