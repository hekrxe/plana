package com.arkr.kafka.network

import java.nio.ByteBuffer
import java.nio.channels.{SelectionKey, SocketChannel}

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
class KChannel(val channel: SocketChannel, val key: SelectionKey) {
  private var send: Send = _

  def setSend(send: Send): Unit = {
    if (null != send) {
      throw new RuntimeException("data not sent yet!")
    }
    this.send = send
  }

  private[network] def write(): Unit = {
    send.writeTo(channel)
    if (send.completed) {
      key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE)
    }
  }

  private[network] def read(): Receive = {
    var size = ByteBuffer.allocate(4)
    channel.read(size)
    // 还需考虑小于0这种情况
    val receiveSize = size.getInt()
    size = null
    val buffer = ByteBuffer.allocate(receiveSize)
    channel.read(buffer)

    new Receive(channel, buffer)
  }
}
