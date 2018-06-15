package com.in.action.server;

import com.in.action.codec.ToIntDecoder;
import com.in.action.server.handler.EchoHandler;
import com.in.action.server.handler.IntHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class ServerChannelInitializer extends ChannelInitializer {
    private static final IntHandler intHandler = new IntHandler();

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ToIntDecoder())
                .addLast(intHandler);
    }
}
