package base

/**
  * User: tanhuayou
  * Date: 2018/7/3
  */

trait Action {
  def doAction()
}

trait BBB extends Action {
  abstract override def doAction(): Unit = {
    println("beforeBBB")
    super.doAction()
    println("afterBBB")
  }
}

trait AAA extends Action {
  abstract override def doAction(): Unit = {
    println("beforeAAA")
    super.doAction()
    println("afterAAA")
  }
}

trait XXX {
  self: Action =>
  def xxx(): Unit = {
    println("beforeXXX")
    self.doAction()
    println("afterXXX")
  }

}

class AOP extends Action {
  override def doAction(): Unit = {
    println("Working...")
  }
}


object Main {
  def main(args: Array[String]): Unit = {
    // 更像是一个从后向前继承的一个对象
    val aop = new AOP with AAA with BBB with XXX
    aop.doAction()
    println("=" * 30)
    aop.xxx()
  }
}
