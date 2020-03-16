package ray.eldath.scala.learning

import scala.io.Source
import scala.util.control.NonFatal

object CountLines {
  def main(args: Array[String]): Unit = {
    args foreach countLine
  }

  private def countLine(fileName: String): Unit = {
    var source: Option[Source] = None

    try {
      source = Some(Source.fromFile(fileName))
      val lines = source.map(_.getLines().size).get
      println(s"file $fileName has $lines lines")
    } catch {
      case NonFatal(ex) => println(s"Non-fatal exception $ex")
    } finally {
      for (s <- source) {
        println(s"closing source $fileName")
        s.close()
      }
    }
  }
}
