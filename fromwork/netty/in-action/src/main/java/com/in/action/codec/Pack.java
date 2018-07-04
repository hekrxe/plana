package com.in.action.codec;

import io.netty.buffer.ByteBuf;

/**
 * User: tanhuayou
 * Date: 2018/7/4
 */
public class Pack {
    private static final byte VERSION = 1;

    private short cmd;
    private byte[] body;

    /**
     * 4+1+2+x
     * LEN+VERSION+CMD+BODY
     */
    public void encode(ByteBuf out) {
        out.writeInt(7 + body.length);
        out.writeByte(VERSION);
        out.writeShort(cmd);
        out.writeBytes(body);
    }

    public static Pack decode(ByteBuf in) {
        int oldReadIndex = in.readerIndex();

        int readableBytes = in.readableBytes();
        int length = in.readInt();
        if (length == readableBytes) {
            byte version = in.readByte();
            if (version == VERSION) {
                short cmd = in.readShort();
                if (cmd >= 0) {
                    byte[] bytes = new byte[readableBytes - 7];
                    in.readBytes(bytes);
                    Pack pack = new Pack();
                    pack.cmd = cmd;
                    pack.body = bytes;
                    return pack;
                }
            }
        }

        in.readerIndex(oldReadIndex);
        return null;
    }

    public short getCmd() {
        return cmd;
    }

    public Pack setCmd(short cmd) {
        this.cmd = cmd;
        return this;
    }

    public byte[] getBody() {
        return body;
    }

    public Pack setBody(byte[] body) {
        this.body = body;
        return this;
    }
}
