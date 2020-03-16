import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

def sleep(millis: Long): Unit = {
  Thread sleep millis
}

def doSomething(index: Int) = {
  if (index > 4)
    throw new IllegalArgumentException("some message")
  sleep((math.random() * 1000).toLong)
  index
}

(1 to 5) foreach { e =>
  val future = Future {
    doSomething(e)
  }

  future onComplete {
    case Success(value) => println(s"something $value")
    case Failure(exception) => ""
  }
}

sleep(1000)