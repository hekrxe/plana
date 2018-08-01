package com.netty.ws;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * User: tanhuayou
 * Date: 2018/8/1
 */
public class WSHandler extends SimpleChannelInboundHandler<Object> {
    private static final String WEB_SOCKET_URL = "ws://localhost:6767/websocket";

    private WebSocketServerHandshaker handshaker;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
        } else if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        } else if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("None TextWSFrame");
            throw new RuntimeException("None TextWSFrame");
        } else {
            String text = ((TextWebSocketFrame) frame).text();
            System.out.println("Receive: " + text);
            TextWebSocketFrame response = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + ": " + text);
            // 群发
            NettyConfig.GROUP.writeAndFlush(response);
        }
    }

    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess()
                || !"websocket".equals(request.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = factory.newHandshaker(request);
        if (null == handshaker) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse response) {
        if (response.status().code() != HttpResponseStatus.OK.code()) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            byteBuf.release();
        }
        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if (response.status().code() != HttpResponseStatus.OK.code()) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 客户端与服务端创建链接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.GROUP.add(ctx.channel());
        System.out.println("new connection," + ctx.channel().remoteAddress());
    }


    /**
     * 断开链接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.GROUP.remove(ctx.channel());
        System.out.println("disconnection," + ctx.channel().remoteAddress());
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
