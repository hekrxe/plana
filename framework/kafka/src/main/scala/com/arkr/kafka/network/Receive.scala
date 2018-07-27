package com.arkr.kafka.network

import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
class Receive(val channel: SocketChannel, val buffer: ByteBuffer) {
}
