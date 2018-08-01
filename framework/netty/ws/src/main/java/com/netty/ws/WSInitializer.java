package com.netty.ws;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * User: tanhuayou
 * Date: 2018/8/1
 */
public class WSInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("httpCodec", new HttpServerCodec())
                .addLast("aggregator", new HttpObjectAggregator(65536))
                .addLast("httpChunked", new ChunkedWriteHandler())
                .addLast("handler", new WSHandler());
    }
}
