package com.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * User: tanhuayou
 * Date: 2018/6/1
 */
public class HttpServer {

    public void start() throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        NioEventLoopGroup work = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 4, new DefaultThreadFactory("work"));
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 1000)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.SO_RCVBUF, 1024 * 1024)
                .childOption(ChannelOption.SO_SNDBUF, 1024 * 1024)
                .childOption(ChannelOption.AUTO_READ, false)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);
        bootstrap.childHandler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast("httpCodec", new HttpServerCodec())
                        .addLast("ServerHandler", new HttpServerHandler());
            }
        });

        ChannelFuture future = bootstrap.bind(6767).sync();
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new HttpServer().start();
    }


}
