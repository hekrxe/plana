package com.arkr.akka.fsm

import akka.actor.FSM

import scala.concurrent.duration._


/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
class Buncher extends FSM[State, Data] {

  startWith(Idle, Uninitialized)

  when(Idle) {
    case Event(SetTarget(ref), _) ⇒
      println("when(Idle) Event")
      goto(Active) using Todo(ref, Vector.empty)
  }

  onTransition {
    case Active -> Idle =>
      println("Active -> Idle")
      stateData match {
        case Todo(ref, queue) =>
          println("Todo(ref, queue)")
          ref ! Batch(queue)
        case _ => // nothing to do
      }
  }

  when(Active, stateTimeout = 1 second) {
    case Event(Flush | StateTimeout, t: Todo) =>
      println("Timeout")
      goto(Idle) using t.copy(queue = Vector.empty)
  }

  whenUnhandled {
    case Event(Queue(obj), t@Todo(_, v)) ⇒
      println("Event")
      goto(Active) using t.copy(queue = v :+ obj)

    case Event(e, s) ⇒
      println("received unhandled request {} in state {}/{}", e, stateName, s)
      stay
  }

  initialize()
}
