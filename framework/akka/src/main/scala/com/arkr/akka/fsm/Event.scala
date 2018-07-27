package com.arkr.akka.fsm

import akka.actor.ActorRef

/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
trait Event

//========receive event define========\\
trait ReceivedEvent extends Event

final case class SetTarget(ref: ActorRef) extends ReceivedEvent

final case class Queue(obj: Any) extends ReceivedEvent

case object Flush extends ReceivedEvent


//========sent event define========\\
trait SendEvent extends Event

final case class Batch(obj: Seq[Any]) extends SendEvent
