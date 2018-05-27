package com.dubbo.study.transport;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class TransportConfig {
    private int bossThreadNumber;
    private int workThreadNumber;

    private SocketAddress address;

    public int getBossThreadNumber() {
        return bossThreadNumber <= 0 ? 1 : bossThreadNumber;
    }

    public TransportConfig setBossThreadNumber(int bossThreadNumber) {
        this.bossThreadNumber = bossThreadNumber;
        return this;
    }

    public int getWorkThreadNumber() {
        return workThreadNumber <= 0 ? Runtime.getRuntime().availableProcessors() * 2 : workThreadNumber;
    }

    public TransportConfig setWorkThreadNumber(int workThreadNumber) {
        this.workThreadNumber = workThreadNumber;
        return this;
    }

    public SocketAddress getAddress() {
        return address == null ? new InetSocketAddress(6767) : address;
    }

    public TransportConfig setAddress(SocketAddress address) {
        this.address = address;
        return this;
    }
}
