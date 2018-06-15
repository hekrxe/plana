package com.arkr.akka.demo

import akka.actor.FSM.{Transition, State => _}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging
import com.arkr.akka.fsm.{Active, Batch, Idle, State}

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
  }

}

object MyActor extends App {
  val system = ActorSystem("demo")

  val myActor = system.actorOf(Props[MyActor], "MyActorName")

  myActor ! "AAAAAAA"


}