package ray.eldath.scala.learning.fp

import scala.annotation.{implicitNotFound, tailrec}

object ListByFold {

  import ray.eldath.scala.learning.features.Pipeline._

  def main(args: Array[String]): Unit = {
    val sl = SomewhatList(List(1, 2, 3))

    sl.foldLeft(2)(_ * _) |> println
    sl.reduceLeft(_ * _) |> println
    sl.map(_ * 2) |> println
    sl.flatMap(e => SomewhatList(List(e * 2))) |> println
    sl.foreach(e => print(s"$e, "))
  }
}

case class SomewhatList[T](val l: Seq[T]) {
  @tailrec
  final def foldLeft[R](initial: R)(op: (R, T) => R): R = l match {
    case head +: tail => SomewhatList(tail).foldLeft(op(initial, head))(op)
    case _ => initial
  }

  final def reduceLeft[R <: T](f: (T, T) => R): R =
    foldLeft(1.asInstanceOf[R])((t1, t2) => f(t1, t2)) // so, where is the somewhat fixed point trait...

  final def map[R](transformer: T => R): SomewhatList[R] =
    SomewhatList(foldLeft(Seq.empty[R])((s, o) => s :+ transformer(o)))

  final def flatMap[R](generator: T => SomewhatList[R]): SomewhatList[R] =
    map(generator).flatten

  final def flatten[I](implicit @implicitNotFound("type ${I} is not a wrapped type") evidence: T <:< SomewhatList[I]): SomewhatList[I] =
    foldLeft(SomewhatList(Seq.empty[I]))((s1, s2) => SomewhatList(s1.l ++ s2.l))

  final def foreach(f: T => Unit): Unit =
    foldLeft(None)((_, e) => {
      f(e)
      None
    })
}

object SomewhatList {
  def apply[T](l: Seq[T]): SomewhatList[T] = new SomewhatList(l)
}
