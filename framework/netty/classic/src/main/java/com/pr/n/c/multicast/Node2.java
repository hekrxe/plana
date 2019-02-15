package com.pr.n.c.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author tanhuayou on 2019/01/31
 */
public class Node2 {
    public static void main(String[] args) throws Exception {
        int port = 6767;
        InetAddress group = InetAddress.getByName("228.0.0.4");
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(group);
        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        }
    }
}
