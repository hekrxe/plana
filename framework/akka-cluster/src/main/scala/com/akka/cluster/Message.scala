package com.akka.cluster

/**
  * User: tanhuayou  
  * Date: 2018/6/24
  */

trait Message

case class WorkAlready() extends Message
