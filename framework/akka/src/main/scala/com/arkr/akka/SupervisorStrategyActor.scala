package com.arkr.akka

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}
import akka.event.Logging

import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}

/**
  * 监管者的监管策略
  * User: tanhuayou  
  * Date: 2018/6/21
  */
class SupervisorStrategyActor extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case "Start" =>
      val sonActor = context.actorOf(Props[SonActor], "sonActor")
      sonActor ! 6


    case x: Any =>
      logger.info("{}", x)
  }

  // 重写默认策略
  override val supervisorStrategy: OneForOneStrategy =
    OneForOneStrategy(maxNrOfRetries = 3,
      withinTimeRange = 1 minute) {
      case _: NullPointerException => Restart
      case _: ArithmeticException => Resume // 保留actor当前的状态 并能处理下一条消息
      case _: IllegalArgumentException => Stop
      case _: UnsupportedOperationException => Stop
      case _ => Escalate // 使监督者失效 将处理权上交给上一层来处理
    }
}

class SonActor extends Actor {
  val logger = Logging(context.system, this)


  override def receive: Receive = {
    case 1 => throw new NullPointerException
    case 2 => throw new ArithmeticException
    case 3 => throw new UnsupportedOperationException
    case _ => throw new Exception
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

object SupervisorStrategyActor extends App {
  val system = ActorSystem("supervisorStrategy")
  private val supervisorStrategyActor: ActorRef = system.actorOf(Props[SupervisorStrategyActor], "supervisorStrategyActor")

  supervisorStrategyActor ! "Start"
}