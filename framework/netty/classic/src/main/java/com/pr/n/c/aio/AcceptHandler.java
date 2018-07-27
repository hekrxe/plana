package com.pr.n.c.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * User: tanhuayou
 * Date: 2018/5/31
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Attach> {

    @Override
    public void completed(AsynchronousSocketChannel channel, Attach attachment) {
        attachment.getAsyncServerChannel().accept(attachment, this);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new ReadHandler(channel));
    }


    @Override
    public void failed(Throwable exc, Attach attachment) {
        exc.printStackTrace();
    }
}
