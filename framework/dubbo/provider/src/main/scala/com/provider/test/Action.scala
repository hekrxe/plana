package com.provider.test

/**
  *
  * Created by tanhuayou on 2019/01/09
  */
trait Action {
  protected val name: String = this.getClass.getName

  def doAction(msg: String): String = {
    println(classOf[Action].getName)
    null
  }
}
