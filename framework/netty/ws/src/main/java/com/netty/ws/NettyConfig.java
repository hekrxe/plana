package com.netty.ws;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * User: tanhuayou
 * Date: 2018/8/1
 */
public class NettyConfig {
    /**
     * 存储每一个客户端接入进来时的channel对象
     */
    public static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
