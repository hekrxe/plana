package com.in.action.server;

import com.in.action.codec.CodecAdapter;
import com.in.action.server.handler.EchoHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class ServerChannelInitializer extends ChannelInitializer {
    private final EchoHandler handler = new EchoHandler();

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast("CodecAdapter", new CodecAdapter())
                .addLast(handler);
    }
}
