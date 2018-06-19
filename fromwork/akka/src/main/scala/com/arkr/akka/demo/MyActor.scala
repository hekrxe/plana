package com.arkr.akka.demo

import akka.actor.FSM.{Transition, State => _}
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import com.arkr.akka.fsm.Batch

/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
class MyActor extends Actor {

  val logger = Logging(context.system, this)

  override def receive: Receive = {

    case q@Batch(_) =>
      logger.info(s"${q.obj}")
    case Transition(ref, from, to) =>
      logger.info(s"$ref,$from,$to")
    //    case t: Transition[State] => // 这样写 jvm 会做类型擦除
    //      logger.info(s"${t.fsmRef},${t.from},${t.to}")
    case x: Any =>
      val hi = context.system.settings.config.getString("you.self.config.hi")
      println(hi)

      val nihao = context.system.settings.config.getString("you.self.config.nihao")
      println(nihao)


      println(Thread.currentThread().getName + s"--$x")
  }

}

object MyActor extends App {
  val system = ActorSystem("demo")

  val myActor = system.actorOf(Props[MyActor], "MyActorName")

  myActor ! "AAAAAAA"


}