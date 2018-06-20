package com.arkr.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

/**
  * User: tanhuayou  
  * Date: 2018/6/20
  */
class LifecycleActor extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case x: Any =>
      logger.info("{}", x)
  }

  // actor 对象创建后
  // actor 启动时
  // 做一些初始化操作
  override def preStart(): Unit = {
    logger.info("preStart")
    super.preStart()
  }

  // actor 停止运行时
  // 做一些清理操作
  override def postStop(): Unit = {
    logger.info("postStop")
    super.postStop()
  }

  // 一般不需要重写
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    logger.info("preRestart")
    super.preRestart(reason, message)
  }

  // 一般不需要重写
  override def postRestart(reason: Throwable): Unit = {
    logger.info("postRestart")
    super.postRestart(reason)
  }
}

object LifecycleActor extends App {
  val system = ActorSystem("LifecycleActor")

  private val lifecycleActor: ActorRef = system.actorOf(Props[LifecycleActor], "lifecycleActor")

  system.stop(lifecycleActor)
}
