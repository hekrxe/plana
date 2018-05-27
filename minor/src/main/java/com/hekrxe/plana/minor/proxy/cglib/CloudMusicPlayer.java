package com.hekrxe.plana.minor.proxy.cglib;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class CloudMusicPlayer {
    private String abx;

    public CloudMusicPlayer(String abx) {
        this.abx = abx;
    }

    public String play(String author) {
        return "云音乐正在播放 " + author + " 的音乐 " + abx;
    }
}
