package ray.eldath.scala.learning.features

object PatternMatching {

  def matchingParameter(y: Int): Unit = {
    for {
      e <- Seq(1, 3, 5.0, "a")
    } {
      e match {
        case `y` | 3 => println(s"match $y or 3")
        case "a" => println("match a")
        case _ => println("match other")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    matchingParameter(1)
  }
}

object MatchingTuple {

  private case class Alpha[A, B](a: A, b: B)

  implicit class Also[T, R](t: T) {
    def also(func: T => R): T = {
      func(t)
      t
    }
  }

  def main(args: Array[String]): Unit = {
    val test: Int Alpha String = Alpha(1, "abc")
    test match {
      case _ Alpha _ => println("match!")
    }

    val itemCosts = Seq(("Pencil", 0.5), ("Paper", 0.02), ("Notebook", 0.8))
    for (e <- itemCosts.zipWithIndex.also(_ => println("computed"))) {
      e match {
        case ((name, prize), index) => println(s"$index: $name - $prize")
      }
    }
  }
}

object UnapplySeqTest {
  def window[T](seq: Seq[T]): String = seq match {
    case head1 +: head2 +: tail => s"($head1, $head2) " + window(tail)
    case head +: tail => s"($head, _) " + window(tail)
    case Nil => "Nil"
  }

  def main(args: Array[String]): Unit = {
    println(window(Seq(1, 5, 6)))
  }
}

//

object SqlOp extends Enumeration {
  type SqlOp = Value

  val EQ = Value("=")
  val IN = Value("IN")
}

import ray.eldath.scala.learning.features.SqlOp._

// where x op v
case class WhereOp[T](column: String, op: SqlOp, value: T)

// where x in (value1, values...)
case class WhereIn[T](column: String, value1: T, values: T*)

object VarargMatching {
  def main(args: Array[String]): Unit = {
    val whereIn = WhereIn("role", 0, 1, 3, 4)
    val conditions = Seq(WhereOp("username", SqlOp.EQ, "Ray Eldath"), whereIn, WhereIn("permission", "U_C"))

    for (condition <- conditions) {
      val r = condition match {
        case WhereOp(column, op, value) => s"where $column $op $value"
        case WhereIn(column, value) => s"where $column = $value"
        case WhereIn(column, value, values@_*) => {
          val a = (value +: values).mkString(", ")
          s"where $column in ($a)"
        }
      }
      println(r)
    }

    val w@WhereIn(column, value1, values@_*) = whereIn
    println(w)
    println(column)
    println(value1)
    println(values)

    if (whereIn == WhereIn("role", 1))
      println("true")
  }
}