package com.arkr.kafka.network

import java.net.InetSocketAddress
import java.nio.channels.{SelectionKey, SocketChannel}

import com.arkr.kafka.NetUtil

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
class Selector {
  private val nioSelector = java.nio.channels.Selector.open
  private val connectedChannel = mutable.ListBuffer[SocketChannel]()
  private val receives = mutable.ListBuffer[Receive]()
  private val channels = new mutable.HashMap[String, KChannel]()

  def connect(address: InetSocketAddress): Unit = {
    val channel = SocketChannel.open()
    channel.configureBlocking(false)
    val socket = channel.socket()
    socket.setKeepAlive(true)
    socket.setReceiveBufferSize(1024 * 4)
    socket.setSendBufferSize(1024 * 4)
    socket.setTcpNoDelay(true)
    try {
      val connected = channel.connect(address)
      val key = channel.register(nioSelector, SelectionKey.OP_CONNECT)
      connectedChannel += channel
      val kch = new KChannel(channel, key)
      key.attach(kch)
      channels.put(NetUtil.connectionId(channel.socket()), kch)
      if (connected) {
        key.interestOps(0)
      }
    } catch {
      case e: Exception =>
        println(e.getMessage)
        throw new RuntimeException(e)
    }
  }

  def register(socketChannel: SocketChannel): Unit = {
    val key = socketChannel.register(nioSelector, SelectionKey.OP_READ)
    val kch = new KChannel(socketChannel, key)
    key.attach(kch)
    channels.put(NetUtil.connectionId(socketChannel.socket()), kch)
    connectedChannel += socketChannel
  }

  def wakeup(): Unit = nioSelector.wakeup()

  def close(): Unit = {
    connectedChannel.foreach(channel => {
      try {
        channel.close()
      } catch {
        case t: Throwable =>
          t.printStackTrace()
      }
    })
    nioSelector.close()
  }

  private def close(ch: SocketChannel): Unit = {
    channels.remove(NetUtil.connectionId(ch.socket()))
    ch.close()
  }

  def poll(timeout: Int): Unit = {
    clear()
    val ready = if (timeout <= 0) nioSelector.selectNow() else nioSelector.select(timeout)
    if (ready > 0) {
      val keys = nioSelector.selectedKeys()
      val iter = keys.iterator()
      while (iter.hasNext) {
        val key = iter.next()
        iter.remove()
        val channel = key.attachment().asInstanceOf[KChannel]
        if (key.isConnectable) {
          val finish = channel.channel.finishConnect()
          if (finish) {
            key.interestOps(key.interestOps & ~SelectionKey.OP_CONNECT | SelectionKey.OP_READ)
          }
        }
        if (key.isReadable) {
          receives += channel.read()
        }
        if (key.isWritable) {
          channel.write()
        }
        if (!key.isValid) {
          close(channel.channel)
        }
      }
    }
  }

  def channel(id: String): Option[KChannel] = channels.get(id)

  def send(send: Send): Unit = {
    send.channel.setSend(send)
  }

  def receive: ListBuffer[Receive] = receives

  private def clear(): Unit = {
    receives.clear()
  }

}
