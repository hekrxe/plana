package com.in.action.client;

import com.in.action.client.handler.EchoHandler;
import com.in.action.codec.CodecAdapter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * User: tanhuayou
 * Date: 2018/6/8
 */
public class ClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast("CodecAdapter", new CodecAdapter())
                .addLast(new EchoHandler());
    }
}
