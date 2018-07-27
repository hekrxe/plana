package com.arkr.akka

import akka.actor.{ActorSystem, Props}
import com.arkr.akka.demo.MyActor
import com.arkr.akka.fsm.{Buncher, SetTarget}

/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
object Main extends App {

  val system = ActorSystem()

  val buncher = system.actorOf(Props[Buncher], "Buncher")
  val myActor = system.actorOf(Props[MyActor], "MyActor")


  buncher ! SetTarget(myActor)

  buncher ! akka.routing.Listen(myActor)


  //  val list = List(1, 2, 3, 4, 5)
  //
  //  list foreach print
  //
  //  val list1 = for (a <- list) yield {
  //    val b = a + 1
  //    b * 2
  //  }
  //
  //  list1 foreach println

}
