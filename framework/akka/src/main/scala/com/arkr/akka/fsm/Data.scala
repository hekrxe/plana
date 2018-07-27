package com.arkr.akka.fsm

import akka.actor.ActorRef


/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
sealed trait Data

case object Uninitialized extends Data

final case class Todo(target: ActorRef, queue: Seq[Any]) extends Data
