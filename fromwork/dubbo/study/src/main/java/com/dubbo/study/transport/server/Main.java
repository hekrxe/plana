package com.dubbo.study.transport.server;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        NettyServer server = new NettyServer();
        server.open();

    }
}
