package com.arkr.chime.handler

import com.arkr.chime.cmd.Cmd
import com.arkr.chime.pack.Pack
import io.netty.channel.{ChannelDuplexHandler, ChannelHandler, ChannelHandlerContext}

/**
  * User: tanhuayou  
  * Date: 2018/7/4
  */
@ChannelHandler.Sharable
class NettyHandler extends ChannelDuplexHandler {

  @throws[Exception]
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
    if (!msg.isInstanceOf[Pack]) {
      super.channelRead(ctx, msg)
    } else {
      val pack = msg.asInstanceOf[Pack]
      Cmd.get(pack.cmd)(ctx.channel(), pack.data)
    }
  }

}
