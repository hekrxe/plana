package com.in.action.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * User: tanhuayou
 * Date: 2018/6/8
 */
public class EchoHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 3; ++i) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(random()));
            Thread.sleep(10000);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        System.out.println(in.toString(CharsetUtil.UTF_8));
    }

    private byte[] random() {
        return new byte[]{1, 2, 3, 4};
    }
}
