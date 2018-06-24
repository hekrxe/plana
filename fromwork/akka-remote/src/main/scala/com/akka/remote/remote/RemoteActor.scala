package com.akka.remote.remote

import java.io.File

import akka.actor.{Actor, ActorSystem}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */
class RemoteActor extends Actor {
  val logger = Logging(context.system, this)

  override def preStart(): Unit = {
    logger.info(s"RemoteActor Created! ${self.path}")
    super.preStart()
  }

  override def receive: Receive = {
    case msg: Any =>
      logger.info("RemoteActor receive msg: {}", msg)
      logger.info("sender {}", sender().path)
      sender() ! "hello"
  }

}

object RemoteActor {
  def main(args: Array[String]) {
    val configFile = getClass.getClassLoader.getResource("remote.application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    ActorSystem("RemoteSystem", config)
    println("remote is ready")
  }
}

