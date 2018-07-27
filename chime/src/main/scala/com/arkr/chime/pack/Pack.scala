package com.arkr.chime.pack

import com.arkr.chime.cmd.Cmd
import io.netty.buffer.ByteBuf

/**
  * User: tanhuayou  
  * Date: 2018/7/4
  */
class Pack(val cmd: Int, val data: Array[Byte]) {

  def encode(out: ByteBuf): Unit = {
    val sumLen = 4 + 1 + 4 + data.length
    out.writeInt(sumLen)
    out.writeByte(Pack.version)
    out.writeInt(cmd)
    out.writeBytes(data)
  }

}

object Pack {
  private val version = 1

  def apply(cmd: Int, data: Array[Byte]): Pack = new Pack(cmd, data)

  def decode(in: ByteBuf): Pack = {
    val oldReadIdx = in.readerIndex()

    val allBytes = in.readableBytes()
    if (allBytes >= 4) {
      val sumLength = in.readInt()
      if (allBytes == sumLength) {
        val versionByte = in.readByte()
        if (versionByte == version) {
          val cmd = in.readUnsignedInt().intValue()
          if (Cmd.exist(cmd)) {
            val receive = new Array[Byte](allBytes - 4 - 1 - 4)
            in.readBytes(receive)
            return apply(cmd, receive)
          }
        }
      }
    }

    in.readerIndex(oldReadIdx)
    null
  }

}