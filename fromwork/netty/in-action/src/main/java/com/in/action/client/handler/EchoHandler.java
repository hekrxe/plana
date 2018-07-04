package com.in.action.client.handler;

import com.in.action.codec.Pack;
import io.netty.buffer.ByteBuf;
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
        Pack pack = new Pack();
        pack.setCmd((short) 12);
        pack.setBody(("hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi" +
                " Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi " +
                "Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!hi Pack!" +
                "hi Pack!").getBytes());
        ctx.writeAndFlush(pack);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        System.out.println(in.toString(CharsetUtil.UTF_8));
    }

    private byte[] random() {
        return new byte[]{1, 2, 3, 4};
    }
}
