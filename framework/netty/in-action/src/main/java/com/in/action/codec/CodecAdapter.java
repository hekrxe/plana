package com.in.action.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * User: tanhuayou
 * Date: 2018/7/4
 */
public class CodecAdapter extends ByteToMessageCodec<Pack> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Pack msg, ByteBuf out) throws Exception {
        msg.encode(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Pack pack = Pack.decode(in);
        if (null != pack) {
            out.add(pack);
        }
    }
}
