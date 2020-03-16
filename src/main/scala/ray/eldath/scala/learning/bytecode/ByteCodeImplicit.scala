package ray.eldath.scala.learning.bytecode

class ByteCodeImplicit {

  implicit object IntTag

  implicit object StringTag

  def m(seq: Seq[Int])(implicit ev: IntTag.type) = {}

  def m(seq: Seq[String])(implicit ev: StringTag.type) = {}
}
