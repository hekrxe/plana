package com.arkr.chime.codec

import java.util

import com.arkr.chime.pack.Pack
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageCodec

/**
  * User: tanhuayou  
  * Date: 2018/7/4
  */
class NettyCodec extends ByteToMessageCodec[Pack] {

  override def encode(ctx: ChannelHandlerContext, msg: Pack, out: ByteBuf): Unit = msg.encode(out)

  override def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[AnyRef]): Unit = {
    val pack = Pack.decode(in)
    if (null != pack) {
      out.add(pack)
    }
  }
}
