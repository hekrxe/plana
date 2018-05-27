package com.hekrxe.plana.minor.proxy.statik;

import com.hekrxe.plana.minor.proxy.MusicPlayer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class CloudMusicPlayer implements MusicPlayer {
    @Override
    public String play(String author) {
        return "云音乐开始播放 " + author + " 的音乐";
    }
}
