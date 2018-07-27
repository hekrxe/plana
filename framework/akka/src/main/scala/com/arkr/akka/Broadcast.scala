package com.arkr.akka

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import akka.event.Logging

/**
  * User: tanhuayou  
  * Date: 2018/6/20
  */
class Broadcast {
}

class ActorA extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case x: Any =>
      logger.info("A {}", x)
  }
}

class ActorB extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case x: Any =>
      logger.info("B {}", x)
  }
}

class ActorC extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case x: Any =>
      logger.info("C {}", x)
  }
}

object Broadcast extends App {
  val system = ActorSystem("Broadcast")

  private val actorA: ActorRef = system.actorOf(Props[ActorA], "actorA")
  private val actorB: ActorRef = system.actorOf(Props[ActorB], "actorB")
  private val actorC: ActorRef = system.actorOf(Props[ActorC], "actorC")

  actorA ! "AAA"
  actorB ! "BBB"
  actorC ! "CCC"


  private val selection: ActorSelection = system.actorSelection("/user/*")
  selection ! "Broadcast msg"

  Thread.sleep(1000)
  system.terminate()


}