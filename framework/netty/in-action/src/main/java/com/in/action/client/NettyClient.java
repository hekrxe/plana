package com.in.action.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * User: tanhuayou
 * Date: 2018/6/8
 */
public class NettyClient {

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("localhost", 6767))
                .handler(new ClientChannelInitializer());

        ChannelFuture future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();

        group.shutdownGracefully().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().start();
    }
}
