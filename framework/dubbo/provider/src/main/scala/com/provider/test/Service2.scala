package com.provider.test

import org.springframework.stereotype.Service

/**
  *
  * Created by tanhuayou on 2019/01/09
  */
@Service
class Service2 {

  def service(msg: String): String = {
    val value = s"Service2: $msg"
    println(value)
    value
  }

}
