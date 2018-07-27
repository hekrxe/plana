package com.arkr.kafka

import java.net.Socket

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
object NetUtil {

  def connectionId(socket: Socket): String = s"${socket.getLocalAddress.getHostAddress}:${socket.getLocalPort}-${socket.getInetAddress.getHostAddress}:${socket.getPort}"

}
