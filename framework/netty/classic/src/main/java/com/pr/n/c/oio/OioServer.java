package com.pr.n.c.oio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * User: tanhuayou
 * Date: 2018/5/30
 */
public class OioServer {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6767);
        for (; ; ) {
            Socket client = serverSocket.accept();
            new Thread(() -> {
                try {
                    handle(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void handle(Socket socket) throws IOException {
        SocketChannel channel = socket.getChannel();
        System.out.println("这里返回的Channel 总是 null " + channel);
        // 所以 读写数据只能使用 inputStream 和 outputStream
        // SocketInputStream
        InputStream inputStream = socket.getInputStream();
        // SocketOutputStream
        OutputStream outputStream = socket.getOutputStream();
        try {
            byte[] bytes = new byte[1024];

            while (true) {
                //读取数据阻塞
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                    // echo
                    outputStream.write(bytes, 0, read);
                    outputStream.flush();
                } else {
                    break;
                }
            }
        } finally {
            try {
                System.out.println("socket关闭");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream.close();
            outputStream.close();

        }
    }

    public static void main(String[] args) throws IOException {
        new OioServer().start();
    }

}
