package ray.eldath.scala.learning

import scala.collection.mutable.ListBuffer

trait Observable[State] {
  private val observers = ListBuffer.empty[Observer[State]]

  def addObserver(o: Observer[State]): Unit = observers.addOne(o)

  def notifyUpdates(state: State): Unit = observers foreach (_.receiveUpdate(state))
}

trait Observer[-State] {
  def receiveUpdate(state: State): Unit
}

trait Clickable {
  def click(): Unit
}

class Button(label: String) extends Clickable {

  override def click(): Unit = println(s"button $label clicked")
}

object Button {
  def apply(label: String): Button = new Button(label)
}

trait ObservableClickable extends Clickable with Observable[Clickable] {
  abstract override def click(): Unit = {
    super.click()
    notifyUpdates(this)
  }
}

class ObservableButton(label: String) extends Button(label) with ObservableClickable

//

class ClickCounter extends Observer[Clickable] {
  private var _counts = 0

  def counts: Int = _counts

  override def receiveUpdate(state: Clickable): Unit = _counts += 1
}

object StackableTrait {
  def main(args: Array[String]): Unit = {
    val b: ObservableClickable = new ObservableButton("test")
    b.click()

    val counter = new ClickCounter
    b.addObserver(counter)

    (1 to 5) foreach (_ => b.click())

    println(counter.counts)
  }
}