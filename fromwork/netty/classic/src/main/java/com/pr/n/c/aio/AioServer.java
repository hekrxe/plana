package com.pr.n.c.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * User: tanhuayou
 * Date: 2018/5/31
 */
public class AioServer {

    public void start() throws IOException {
        CountDownLatch latch = new CountDownLatch(1);
        AsynchronousServerSocketChannel asyncServerChannel = AsynchronousServerSocketChannel.open();
        asyncServerChannel.bind(new InetSocketAddress(6767));
        Attach attach = new Attach(latch, asyncServerChannel);
        try {
            asyncServerChannel.accept(attach, new AcceptHandler());
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new AioServer().start();
    }
}
