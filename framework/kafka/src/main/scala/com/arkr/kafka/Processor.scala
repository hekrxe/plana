package com.arkr.kafka

import java.nio.channels.SocketChannel
import java.util.concurrent.ConcurrentLinkedQueue

import com.arkr.kafka.network.{Selector => KSelector}


/**
  * 主要完成读取和响应，不参与业务逻辑处理
  * User: tanhuayou  
  * Date: 2018/7/5
  */
class Processor(val channelIdx: Int,
                channel: RequestResponseChannel) extends AbstractServerThread {

  private val newConnections = new ConcurrentLinkedQueue[SocketChannel]()

  private val selector = new KSelector


  override def wakeup(): Unit = selector.wakeup()

  override def run(): Unit = {
    startupComplete()
    while (isRunning) {
      configureNewConnections()
      poll()
      processCompletedReceives()
    }

  }

  private def processCompletedReceives(): Unit = {
    selector.receive.foreach { recv =>
    }
  }

  private def poll(): Unit = {
    try {
      selector.poll(300)
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  private def configureNewConnections(): Unit = {
    while (!newConnections.isEmpty) {
      val channel = newConnections.poll()
      selector.register(channel)
    }
  }

  def accept(socketChannel: SocketChannel) {
    newConnections.add(socketChannel)
    wakeup()
  }
}
