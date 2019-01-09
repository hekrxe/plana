package com.provider.test

import org.springframework.beans.factory.annotation.Autowired

/**
  *
  * Created by tanhuayou on 2019/01/09
  */
trait HiAction extends Action {
  @Autowired private var service1: Service1 = _

  override def doAction(msg: String): String = {
    println(classOf[HiAction].getName)
    super.doAction(service1.service(msg))
  }
}
