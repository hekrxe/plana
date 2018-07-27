package com.akka.cluster

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorLogging, ActorRef}

import scala.collection.mutable

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */
class ClientActor extends Actor with ActorLogging {
  private val workNodes = new mutable.ListBuffer[ActorRef]()
  private val robin = new AtomicInteger(0)


  override def receive: Receive = {
    case WorkAlready =>
      workNodes += sender()
    case msg: String =>
      if (workNodes.nonEmpty) {
        workNodes(robin.getAndIncrement() % workNodes.size) ! msg
      } else {
        log.info("unsupported receive msg {}", msg)
      }
  }
}
