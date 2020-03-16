package ray.eldath.scala.learning.features

class Implicitly[A](list: List[A]) {
  def sortBy1[B](f: A => B)(implicit order: Ordering[B]): List[A] =
    list.sortBy(f)(order)

  def sortBy2[B: Ordering](f: A => B): List[A] = list.sortBy(f)(implicitly[Ordering[B]])
}

object Implicitly {
  def main(args: Array[String]): Unit = {
    val l = Implicitly(List(1, 4, 6, 9))
    println(l.sortBy1(-_))
    println(l.sortBy2(-_))
  }

  def apply[A](list: List[A]): Implicitly[A] = new Implicitly(list)
}

object ImplicitEvidence {
  def main(args: Array[String]): Unit = {
    MyList(List(1 -> 2)).t
    //    MyList(List(1, 2)).t
  }

  class MyList[A](list: List[A]) {
    def t[K, V](implicit evidence: A <:< (K, V)): Unit = // implicit evidence: just verify type A
      println("checked!")
  }

  object MyList {
    def apply[A](list: List[A]): MyList[A] = new MyList(list)
  }

}

object Pipeline {

  implicit class Piped[A](val value: A) extends AnyVal {
    def |>[B](f: A => B): B = f(value)
  }

  def main(args: Array[String]): Unit = {
    List(123, 456) |> println
  }
}

trait TypeClassTrait {
  def printMe(): Unit
}

object TypeClassTrait {

  implicit class PrintString(str: String) extends TypeClassTrait {
    override def printMe(): Unit = println(s"printMe: $str")
  }

  implicit class PrintInt(int: Int) extends TypeClassTrait {
    override def printMe(): Unit = println(s"printMeInt: $int")
  }


  def main(args: Array[String]): Unit = {
    "123".printMe()
    123.printMe()
  }
}