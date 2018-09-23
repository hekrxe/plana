package base

/**
  * User: tanhuayou
  * Date: 2018/8/27
  */
class DoAction {

  def aa(a: Int, b: Int): Unit = doAction {
    println(a + b)
  }

  private def doAction(f: => Unit): Unit = {

    println("pre")
    f
    println("last")
  }

}

object DoAction extends App {
  val action = new DoAction
  action.aa(12, 43)

}
