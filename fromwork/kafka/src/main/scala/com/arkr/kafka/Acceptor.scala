package com.arkr.kafka

import java.net.{InetSocketAddress, SocketException}
import java.nio.channels.{SelectionKey, Selector, ServerSocketChannel}

/**
  * 接受客户端的链接请求，创建socket链接请求并分配给Processor
  * User: tanhuayou  
  * Date: 2018/7/5
  */
class Acceptor(host: String,
               port: Int,
               bufferSize: Int,
               processors: Array[Processor]) extends AbstractServerThread {

  private val nioSelector = Selector.open()
  private val serverChannel = openServerSocket(host, port)

  this.synchronized {
    var idx = 0
    processors.foreach { processor =>
      val thread = new Thread(processor)
      thread.setName(s"kafka-network-processor-$idx")
      thread.setDaemon(false)
      idx += 1
      thread.start()
    }
  }


  private def openServerSocket(host: String, port: Int): ServerSocketChannel = {
    val socketAddress =
      if (host == null || host.trim.isEmpty)
        new InetSocketAddress(port)
      else
        new InetSocketAddress(host.trim, port)
    val serverChannel = ServerSocketChannel.open()
    serverChannel.configureBlocking(false)
    serverChannel.socket().setReceiveBufferSize(bufferSize)
    try {
      serverChannel.socket.bind(socketAddress)
      println("Awaiting socket connections on %s:%d.".format(socketAddress.getHostString, serverChannel.socket.getLocalPort))
    } catch {
      case e: SocketException =>
        throw new RuntimeException("Socket server failed to bind to %s:%d: %s.".format(socketAddress.getHostString, port, e.getMessage), e)
    }
    serverChannel
  }

  override def wakeup(): Unit = nioSelector.wakeup()

  override def run(): Unit = {
    serverChannel.register(nioSelector, SelectionKey.OP_ACCEPT)
    startupComplete()
    try {
      var currentProcessor = 0
      while (isRunning) {
        try {
          val ready = nioSelector.select(500)
          if (ready > 0) {
            val keys = nioSelector.selectedKeys()
            val iter = keys.iterator()
            while (iter.hasNext && isRunning) {
              try {
                val key = iter.next()
                iter.remove()
                if (key.isAcceptable) {
                  accept(key, processors(currentProcessor))
                } else {
                  println("Unrecognized key state for acceptor thread.")
                }
                currentProcessor = (currentProcessor + 1) % processors.length
              } catch {
                case t: Throwable => println("Error while accepting connection")
                  t.printStackTrace()
              }
            }
          }
        } catch {
          case e: Throwable => println(s"Error occurred ${e.getMessage}")
            e.printStackTrace()
        }
      }
    } finally {
      shutdownComplete()
    }
  }

  private def accept(key: SelectionKey, processor: Processor): Unit = {
    val serverSocketChannel = key.cancel().asInstanceOf[ServerSocketChannel]
    val socketChannel = serverSocketChannel.accept()
    try {
      socketChannel.configureBlocking(false)
      socketChannel.socket().setTcpNoDelay(true)
      socketChannel.socket().setKeepAlive(true)
      socketChannel.socket().setSendBufferSize(bufferSize)

      // 交由Processor处理
      processor.accept(socketChannel)
    } catch {
      case e: Exception =>
        println(e.getMessage)
        close(socketChannel)
    }
  }
}
