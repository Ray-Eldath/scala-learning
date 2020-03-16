package ray.eldath.scala.learning

import scala.io.Source
import scala.language.reflectiveCalls
import scala.util.control.NonFatal

object ResourceManager {

  object manage {

    def apply[R <: {def close(): Unit}, T](resource: => R)(handler: R => T): Unit = {
      var res: Option[R] = None

      try {
        res = Some(resource)
        handler(res.get)
      } catch {
        case NonFatal(ex) => println(s"Non fatal exception $ex")
      } finally {
        for (s <- res) {
          println("closing resource...")
          s.close()
        }
      }
    }
  }

}

object ManagedCountLines {

  import ResourceManager.manage

  def main(args: Array[String]): Unit = {
    args foreach (f => {
      manage(Source.fromFile(f)) { s =>
        println(s"file $f have ${s.getLines().size} lines.")
      }
    })
  }
}