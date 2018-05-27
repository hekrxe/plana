package com.dubbo.study.transport.server;

import com.dubbo.study.transport.TransportConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.SystemPropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class NettyServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static final String NAME_LINUX = "linux";
    private static final String NAME_MAC = "mac";
    private static final String NAME_BSD = "bsd";
    private static final String NAME_DARWIN = "darwin";

    private TransportConfig config;
    private ServerBootstrap bootstrap;
    private Class<? extends ServerSocketChannel> serverSocketChannel;
    private EventLoopGroup boss;
    private EventLoopGroup work;
    private Channel bossChannel;

    public NettyServer() {
        this(null);
    }

    public NettyServer(TransportConfig config) {
        if (null == config) {
            config = new TransportConfig();
        }
        this.config = config;
    }


    private void pick() {
        String name = SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim();
        if (name.startsWith(NAME_LINUX)) {
            serverSocketChannel = EpollServerSocketChannel.class;
            boss = new EpollEventLoopGroup(config.getBossThreadNumber(), new DefaultThreadFactory("boss"));
            work = new EpollEventLoopGroup(config.getWorkThreadNumber(), new DefaultThreadFactory("work"));
        } else if (name.startsWith(NAME_MAC) || name.contains(NAME_BSD) || name.startsWith(NAME_DARWIN)) {
            serverSocketChannel = KQueueServerSocketChannel.class;
            boss = new KQueueEventLoopGroup(config.getBossThreadNumber(), new DefaultThreadFactory("boss"));
            work = new KQueueEventLoopGroup(config.getWorkThreadNumber(), new DefaultThreadFactory("work"));
        } else {
            serverSocketChannel = NioServerSocketChannel.class;
            boss = new NioEventLoopGroup(config.getBossThreadNumber(), new DefaultThreadFactory("boss"));
            work = new NioEventLoopGroup(config.getWorkThreadNumber(), new DefaultThreadFactory("work"));
        }
    }


    public void open() throws InterruptedException {
        pick();
        bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(serverSocketChannel)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ServerChannelInitializer());
        ChannelFuture channelFuture = bootstrap.bind(config.getAddress()).sync();
        bossChannel = channelFuture.channel();
    }

    public void close() {
        if (null != bossChannel) {
            try {
                bossChannel.close().sync();
            } catch (InterruptedException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        if (null != bootstrap) {
            if (null != boss) {
                boss.shutdownGracefully().syncUninterruptibly();
            }
            if (null != work) {
                work.shutdownGracefully().syncUninterruptibly();
            }
        }
    }

}
