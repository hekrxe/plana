package com.arkr.chime.cmd

import io.netty.channel.Channel

import scala.collection.mutable


/**
  * User: tanhuayou  
  * Date: 2018/7/4
  */
object Cmd {
  type fun = (Channel, Array[Byte]) => Unit

  private val defFuc = (ch: Channel, data: Array[Byte]) => println(s"remote=[${ch.remoteAddress()}] cmd=[0],data=${new String(data)}")

  private val cmdCallback = new mutable.HashMap[Int, fun]()
  cmdCallback(0) = defFuc


  def register(cmdNumber: Int, f: fun): Unit = {
    val fun = cmdCallback.get(cmdNumber)
    if (fun.isDefined) {
      throw new RuntimeException(s"cmd:[$cmdNumber] already exist")
    }
    cmdCallback.put(cmdNumber, f)
  }

  def exist(cmd: Int): Boolean = cmdCallback.contains(cmd)

  private[chime] def get(cmd: Int): fun = cmdCallback.getOrElse(cmd, defFuc)
}