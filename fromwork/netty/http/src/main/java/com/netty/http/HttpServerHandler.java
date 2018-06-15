package com.netty.http;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpUtil.setKeepAlive;

final class HttpServerHandler extends ChannelDuplexHandler {
    private boolean persistentConnection = true;
    private int pendingResponses;
    private boolean mustRecycleEncoder;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        System.out.println("New http connection, requesting read");
        ctx.read();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // read message and track if it was keepAlive
        if (msg instanceof HttpRequest) {
            final HttpRequest request = (HttpRequest) msg;

            DecoderResult decoderResult = request.decoderResult();
            if (decoderResult.isFailure()) {
                Throwable cause = decoderResult.cause();
                System.out.println("Decoding failed: " + msg + " : ");
                cause.printStackTrace();

                HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                        cause instanceof TooLongFrameException
                                ? HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE
                                : HttpResponseStatus.BAD_REQUEST
                );
                response.headers()
                        .setInt(HttpHeaderNames.CONTENT_LENGTH, 0)
                        .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                ctx.writeAndFlush(response)
                        .addListener(ChannelFutureListener.CLOSE);
                return;
            } else {
                ctx.writeAndFlush("hi");
            }
        } else {
            System.out.println("None http");
        }
        ctx.fireChannelRead(msg);
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
            throws Exception {
        // modify message on way out to add headers if needed
        if (msg instanceof HttpResponse) {
            final HttpResponse response = (HttpResponse) msg;
            // Server might think it can keep connection alive, but we should fix response header if we know better
            if (!shouldKeepAlive()) {
                setKeepAlive(response, false);
            }

            if (response.status().equals(HttpResponseStatus.CONTINUE)) {
                ctx.write(msg, promise);
                return;
            }
        }
        if (msg instanceof LastHttpContent) {
            if (!shouldKeepAlive()) {
                promise.addListener(ChannelFutureListener.CLOSE);
                ctx.write(msg, promise);
                return;
            }

            ctx.write(msg, promise);

            if (!persistentConnection) {
                return;
            }

            if (mustRecycleEncoder) {
                mustRecycleEncoder = false;
                pendingResponses -= 1;
            }

            return;
        }
        ctx.write(msg, promise);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }


    boolean shouldKeepAlive() {
        return pendingResponses != 0 && persistentConnection;
    }

}
