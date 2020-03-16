package ray.eldath.scala.learning.features

trait Functor[A, +M[_]] {
  def map1[B](f: A => B): M[B]
}

object Functor {

  implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A, Option] {
    override def map1[B](f: A => B): Option[B] = if (opt.isEmpty) None else Option(f(opt.get))
  }

  private type MapValue[V] = Map[_, V]

  private implicit class MapFunctor[K, V1](map: Map[K, V1]) extends Functor[V1, MapValue] {
    override def map1[B](f: V1 => B): Map[K, B] =
      for {
        e <- map
      } yield e._1 -> f(e._2)
  } // private is for avoid implicit ambiguity.

  // this is what we are familiar with! Described in *Cats*, use type alias for renaming "two-hole"
  // type to "one-hole" type is a basic technique. Type lambda can do this as well, but no special
  // type alias needed to be declared:
  implicit class MapFunctorWithTypeLambda[K, V1](map: Map[K, V1]) extends Functor[V1, ({type λ[α] = Map[K, α]})#λ] {
    override def map1[B](f: V1 => B): Map[K, B] = MapFunctor(map) map1 f
  }

}

object TypeLambda {

  import Functor._

  def main(args: Array[String]): Unit = {
    println(Some(2) map1 (_ * 5))

    val a: Option[Int] = None
    println(a map1 (_ + 10))

    val m = Map(1 -> "a", 2 -> "b")
    println(m map1 (_.toUpperCase()))
  }
}