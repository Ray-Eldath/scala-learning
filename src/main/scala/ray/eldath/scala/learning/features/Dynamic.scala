package ray.eldath.scala.learning.features

import scala.language.dynamics

case class CheapActiveRecord[T](records: Seq[Map[String, T]]) extends Dynamic {
  private val availableKeys = records.flatMap(_.keys)

  def selectDynamic(name: String): CheapActiveRecord[T] =
    name match {
      case "all" => this
      case "first" => CheapActiveRecord(Seq(records.head))
      case _ => {
        val filters = if (name.contains("_and_")) name.split("_and_").toSeq else Seq(name)
        filters.find(!availableKeys.contains(_))
          .foreach { name => throw NoSuchKeyException(s"key $name is not found in the record") }

        val result =
          for {
            map <- records
            selected = map.filter { case (key, _) => filters.contains(key) }
          } yield selected

        CheapActiveRecord(result)
      }
    }

  override def toString: String =
    records.map {
      _.map {
        case (key, value) => s"$key = $value"
      }.mkString(", ")
    }.mkString("[", ",\n  ", "]\n")
}

case class NoSuchKeyException(message: String) extends Exception(message)

object CheapActiveRecord {
  private def m(name: String, languages: Seq[String], IQ: Int): Map[String, Any] =
    Map("name" -> name, "languages" -> languages, "IQ" -> IQ)

  def main(args: Array[String]): Unit = {
    val ar = CheapActiveRecord(
      Seq(
        m("Ray Eldath", Seq("Kotlin", "Scala"), IQ = Int.MinValue),
        m("ice1000", Seq("countless"), IQ = Int.MaxValue),
        m("Phosphorous", Seq("countless, not only for-machine but also for-human"), IQ = Int.MaxValue)
      ))

    println(s"all: \n${ar.all}")
    println(s"first: \n${ar.first}")
    println(s"name: \n${ar.name}")
    println(s"name_and_IQ: \n${ar.name_and_IQ}")
  }
}
