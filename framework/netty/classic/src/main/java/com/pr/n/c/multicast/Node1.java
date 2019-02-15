package com.pr.n.c.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

/**
 * 组播能在公网传播(指定组了)
 * 广播只能在局域网传播
 * @author tanhuayou on 2019/01/31
 */
public class Node1 {
    public static void main(String[] args) throws Exception {
        int port = 6767;
        InetAddress group = InetAddress.getByName("228.0.0.6");
        MulticastSocket multicastSocket = new MulticastSocket(port);
        multicastSocket.joinGroup(group);
        byte[] message = "Hello from Node1".getBytes(StandardCharsets.UTF_8);
        while (true) {
            DatagramPacket packet = new DatagramPacket(message, message.length, group, port);
            multicastSocket.send(packet);
            Thread.sleep(1000);
        }
    }
}
