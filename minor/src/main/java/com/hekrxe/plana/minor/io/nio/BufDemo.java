package com.hekrxe.plana.minor.io.nio;

import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: tanhuayou
 * Date: 2018/5/30
 */
public class BufDemo {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = BufDemo.class.getClassLoader();

        String fileName = "demo.txt";
        URL resource = classLoader.getResource(fileName);
        RandomAccessFile file = new RandomAccessFile(resource.getFile(), "r");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(36);
        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        channel.close();

    }
}
