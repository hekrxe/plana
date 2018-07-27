package com.pr.n.c.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * User: tanhuayou
 * Date: 2018/5/30
 */
public class NioServer {
    private Selector selector = Selector.open();

    public NioServer() throws IOException {
    }

    public void start() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(6767));

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        for (; ; ) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();
                handle(next);
            }
        }
    }

    private void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(32);

            int read = channel.read(buffer);
            while (read > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.rewind();
                // echo
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
                read = channel.read(buffer);
            }
            if (-1 == read) {
                System.out.println("Client Close: " + channel);
                channel.close();
                key.cancel();
            }

        } else {
            System.out.println("un catch key: " + key.interestOps());
        }
    }


    public static void main(String[] args) throws IOException {
        new NioServer().start();
    }
}
