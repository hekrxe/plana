package com.in.action.client.handler;

import com.in.action.codec.Pack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: tanhuayou
 * Date: 2018/6/8
 */
public class EchoHandler extends SimpleChannelInboundHandler<Pack> {

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
    protected void channelRead0(ChannelHandlerContext ctx, Pack pack) throws Exception {
        System.out.println("channelRead0 cmd=[" + pack.getCmd() + "]");
        System.out.println("channelRead0 body=[" + new String(pack.getBody()) + "]");
    }

    private byte[] random() {
        return new byte[]{1, 2, 3, 4};
    }
}
