package com.akka.cluster

import akka.actor.{Actor, ActorLogging}
import akka.cluster.ClusterEvent._
import akka.cluster.{Cluster, Member}

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */
class SimpleClusterListener extends Actor with ActorLogging {
  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart
  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
      classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive: PartialFunction[Any, Unit] = {
    case MemberUp(member) =>
      log.info("Member is Up: {}", member.address)
      register(member)
    case UnreachableMember(member) =>
      log.info("Member detected as unreachable: {}", member)
    case MemberRemoved(member, previousStatus) =>
      log.info("Member is Removed: {} after {}",
        member.address, previousStatus)
    case state: CurrentClusterState =>
      state.members foreach register
    case msg: String =>
      log.info("working on msg: {}", msg)
    case _: MemberEvent => // ignore
  }


  private def register(member: Member): Unit = {
    if (member.hasRole("client")) {
      val selection = context.actorSelection(member.address + "/user/ClientActor")
       selection ! WorkAlready
    }

  }

}
