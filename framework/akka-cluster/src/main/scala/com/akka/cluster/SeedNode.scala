package com.akka.cluster

import akka.actor.{ActorSystem, Address, Props}
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */
object SeedNode extends App {
  val port = 6767
  val config = ConfigFactory.parseString(
    s"""
    akka.remote.netty.tcp.port=$port
  """
  ).withFallback(ConfigFactory.load())
  val actorSystem = ActorSystem("SystemCluster", config)

  Cluster(actorSystem).join(
    Address(
      protocol = "akka.tcp",
      system = "SystemCluster",
      host = "0.0.0.0", // self
      port = port
    )
  )
  actorSystem.actorOf(Props[SimpleClusterListener], name = "clusterListener")
  println("SeedNode Ready")
}

object ClusterNode extends App {
  val port = 7676
  val config = ConfigFactory.parseString(
    s"""
    akka.remote.netty.tcp.port=$port
  """
  ).withFallback(ConfigFactory.load())
  val actorSystem = ActorSystem("SystemCluster", config)
  Cluster(actorSystem).joinSeedNodes(List(
    Address(
      protocol = "akka.tcp",
      system = "SystemCluster",
      host = "0.0.0.0", // remote
      port = 6767 // remote
    )
  ))
  actorSystem.actorOf(Props[SimpleClusterListener], name = "clusterListener")
  println("ClusterNode Ready")
}

object Client extends App {

  val port = 26767
  val config = ConfigFactory.parseString(
    s"""
    akka.remote.netty.tcp.port=$port
  """
  ).withFallback(ConfigFactory.load("client.application.conf"))
  val actorSystem = ActorSystem("SystemCluster", config)
  Cluster(actorSystem).joinSeedNodes(List(
    Address(
      protocol = "akka.tcp",
      system = "SystemCluster",
      host = "0.0.0.0", // remote
      port = 6767 // remote
    )
  ))

  import actorSystem.dispatcher

  import scala.concurrent.duration._
  import scala.language.postfixOps

  val clusterListener = actorSystem.actorOf(Props[ClientActor], name = "ClientActor")

  actorSystem.scheduler.schedule(2 seconds, 2 seconds) {
    clusterListener ! s"hello-${System.currentTimeMillis()}"
  }

}
