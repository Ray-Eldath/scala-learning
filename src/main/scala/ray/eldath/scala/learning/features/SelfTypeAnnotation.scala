package ray.eldath.scala.learning.features

import scala.collection.mutable.ListBuffer

abstract class SubjectObserver {
  type S <: Subject
  type O <: Observer

  trait Observer {
    def receiveUpdate(subject: S): Unit
  }

  trait Subject {
    self: S => // self-type annotation: restrict it to actual type, not parent type

    private val observers = ListBuffer[O]()

    def addObserver(observer: O): Unit = observers.addOne(observer)

    def notifyObservers(): Unit = observers foreach (_.receiveUpdate(self))
  }

}

trait Persistence {
  def startPersistence(): Unit = println("starting persistence tier...")
}

trait UI {
  def startUI(): Unit = println("starting UI...")
}

trait App {
  self: Persistence with UI => // self-type annotation: inheritance must conform such mixin

  def run(): Unit = {
    startPersistence()
    startUI()
  }
}

// this is called cake pattern, which is still somewhat controversial. So use it only necessary.

object MyApp extends App with Persistence with UI {

  def main(args: Array[String]): Unit = {
    run()
  }
}

trait AnotherApp extends Persistence with UI {
  def run(): Unit = {
    startPersistence()
    startUI()
  }
}

object MyAnotherApp extends AnotherApp {
  def main(args: Array[String]): Unit = {
    run()
  }
}