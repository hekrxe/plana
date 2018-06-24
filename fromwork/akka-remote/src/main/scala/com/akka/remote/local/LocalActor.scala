package com.akka.remote.local

import java.io.File

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import com.akka.remote.local.LocalActor.Start
import com.akka.remote.remote.RemoteActor
import com.typesafe.config.ConfigFactory

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */
class LocalActor extends Actor {
  val logger = Logging(context.system, this)

  //  // 创建远程Actor的方式1 这种方式事先要先创建actor
  //    override def preStart(): Unit = {
  //      val remoteActor = context.actorSelection("akka.tcp://RemoteSystem@127.0.0.1:6767/user/remoteActor")
  //      println("That 's remote:" + remoteActor)
  //      remoteActor ! "hi"
  //    }

  override def receive: Receive = {
    case Start =>
      // 方式2
      // 注意 这里必须是 system.actorOf去获取远程Actor
      // 否则 如果是 context.actorOf 那么仅仅是在本地创建了一个 Actor
      val remote = context.system.actorOf(Props[RemoteActor], "remoteActor")
      remote ! "hi"
    case msg: Any =>
      logger.info("localActor receive msg {}", msg)
  }
}

object LocalActor {

  case object Start

  def main(args: Array[String]) {
    val configFile = getClass.getClassLoader.getResource("local.application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("LocalSystem", config)
    val localActor = system.actorOf(Props[LocalActor], name = "local")
    localActor ! Start
  }
}