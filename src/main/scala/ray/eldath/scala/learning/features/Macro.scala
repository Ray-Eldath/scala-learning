package ray.eldath.scala.learning.features

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object Macro {

  object fixed {
    def apply[T](checker: => Boolean)(block: => T): T = macro impl

    def impl(c: blackbox.Context)(checker: c.Tree)(block: c.Tree): c.universe.Tree = {
      import c.universe._

      val checkerCode = showCode(checker)
      val q"..$statements" = block
      val generatedStatements = statements.map { statement =>
        val message = s"fixed variable modified, for statement: ${showCode(statement)}"
        val exception = q"throw new ray.eldath.scala.learning.features.Macro.FixedVariableModifiedException($message)"
        q"{ val sidecar = $statement; if(false == $checker) $exception; sidecar };"
      }

      val message = s"assertion failed for checker $checkerCode"
      val exception = q"throw new java.lang.AssertionError($message)"
      val pre = q"if (false == $checker) $exception"

      val generated = q"$pre; ..$generatedStatements"
      println(showCode(generated))
      generated
    }
  }

  class FixedVariableModifiedException(message: String) extends Exception(message)

}
