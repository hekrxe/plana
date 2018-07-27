package com.arkr.kafka.network

import java.nio.channels.GatheringByteChannel

/**
  * User: tanhuayou  
  * Date: 2018/7/5
  */
trait Send {

  def writeTo(channel: GatheringByteChannel)

  def completed: Boolean

  def channel: KChannel

}
