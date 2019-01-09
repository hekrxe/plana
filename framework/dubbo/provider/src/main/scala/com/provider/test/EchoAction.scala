package com.provider.test

import org.springframework.beans.factory.annotation.Autowired

/**
  *
  * Created by tanhuayou on 2019/01/09
  */
trait EchoAction extends Action {
  @Autowired private var service2: Service2 = _

  override def doAction(msg: String): String = {
    println(classOf[EchoAction].getName)
    service2.service(msg)
    super.doAction(msg)
  }
}
