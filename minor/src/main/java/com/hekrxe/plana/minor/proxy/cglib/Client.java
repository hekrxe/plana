package com.hekrxe.plana.minor.proxy.cglib;

/**
 * User: tanhuayou
 * Date: 2018/5/26
 */
public class Client {
    public static void main(String[] args) {
        CloudMusicPlayerProxy proxy = new CloudMusicPlayerProxy();
        CloudMusicPlayer player = proxy.getObject();
        String sing = player.play("陈奕迅");
        System.out.println(sing);
    }
}
