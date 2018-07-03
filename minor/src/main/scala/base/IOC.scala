package base

/**
  * User: tanhuayou  
  * Date: 2018/7/3
  */
trait Logger {
  def log(msg: String)
}

trait Auth {
  auth: Logger =>
  def act(msg: String): Unit = log(msg)
}

object DI extends Auth with Logger {
  override def log(msg: String): Unit = {
    println(s"DI: $msg")
  }
}

object IOC extends App {
  DI.log("log")
  DI.act("act")
}
