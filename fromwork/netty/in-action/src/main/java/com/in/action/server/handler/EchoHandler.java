package com.in.action.server.handler;

import com.in.action.codec.Pack;
import io.netty.channel.*;

import java.net.InetSocketAddress;

/**
 * User: tanhuayou
 * Date: 2018/6/8
 */
@ChannelHandler.Sharable
public class EchoHandler extends ChannelDuplexHandler {
    private Dispatcher dispatcher = new Dispatcher();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(unique(ctx.channel()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println(unique(ctx.channel()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof Pack)) {
            super.channelRead(ctx, msg);
            return;
        }
        dispatcher.dispatch((Pack) msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof Pack)) {
            super.write(ctx, msg, promise);
            return;
        }
        Pack pack = (Pack) msg;
        System.out.println("Write cmd: " + pack.getCmd());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(unique(ctx.channel()));
        ctx.channel().closeFuture();
        cause.printStackTrace();
    }


    private String unique(Channel channel) {
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

}
