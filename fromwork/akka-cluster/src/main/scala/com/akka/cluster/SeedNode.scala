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

  Cluster(actorSystem).join(Address(
    protocol = "akka.tcp",
    system = "SystemCluster",
    host = "127.0.0.1", // self
    port = port)
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
    Address(protocol = "akka.tcp",
      system = "SystemCluster",
      host = "127.0.0.1", // remote
      port = 6767 // remote
    )
  ))
  actorSystem.actorOf(Props[SimpleClusterListener], name = "clusterListener")
  println("ClusterNode Ready")
}
