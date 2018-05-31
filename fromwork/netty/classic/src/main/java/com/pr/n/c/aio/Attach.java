package com.pr.n.c.aio;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * User: tanhuayou
 * Date: 2018/5/31
 */
public class Attach {
    private CountDownLatch watch;
    private AsynchronousServerSocketChannel asyncServerChannel;

    public Attach(CountDownLatch watch, AsynchronousServerSocketChannel asyncServerChannel) {
        this.watch = watch;
        this.asyncServerChannel = asyncServerChannel;
    }

    public CountDownLatch getWatch() {
        return watch;
    }

    public Attach setWatch(CountDownLatch watch) {
        this.watch = watch;
        return this;
    }

    public AsynchronousServerSocketChannel getAsyncServerChannel() {
        return asyncServerChannel;
    }

    public Attach setAsyncServerChannel(AsynchronousServerSocketChannel asyncServerChannel) {
        this.asyncServerChannel = asyncServerChannel;
        return this;
    }
}
