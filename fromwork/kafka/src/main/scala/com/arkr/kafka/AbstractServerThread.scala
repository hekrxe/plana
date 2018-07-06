package com.arkr.kafka

import java.nio.channels.SocketChannel
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
abstract class AbstractServerThread extends Runnable {
  private val startupLatch = new CountDownLatch(1)
  private val shutdownLatch = new CountDownLatch(1)
  private val alive = new AtomicBoolean(true)

  def wakeup()

  def shutdown(): Unit = {
    alive.set(false)
    wakeup()
    shutdownLatch.await()
  }


  def awaitStartup(): Unit = {
    startupLatch.await()
  }

  def close(channel: SocketChannel): Unit = {
    if (null != channel) {
      channel.socket().close()
      channel.close()
    }
  }

  protected def startupComplete(): Unit = {
    startupLatch.countDown()
  }

  protected def shutdownComplete(): Unit = {
    shutdownLatch.countDown()
  }

  protected def isRunning: Boolean = alive.get()
}
