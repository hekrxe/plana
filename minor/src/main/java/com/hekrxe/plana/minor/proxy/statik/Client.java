package com.hekrxe.plana.minor.proxy.statik;

import com.hekrxe.plana.minor.proxy.MusicPlayer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class Client {
    public static void main(String[] args) {
        String sing = get().play("陈奕迅");
        System.out.println(sing);
    }

    /**
     * 客户端去获取一个 能播放 音乐的途径
     *
     * @return music
     */
    private static MusicPlayer get() {
        CloudMusicPlayer cloudMusic = new CloudMusicPlayer();
        CloudMusicPlayerProxy cloudMusicProxy = new CloudMusicPlayerProxy(cloudMusic);
        /*
         * 如果此时还想代理QQ音乐 就得重新构造 QQ音乐的代理
         */
        return cloudMusicProxy;
    }
}
