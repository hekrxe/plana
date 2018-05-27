package com.hekrxe.plana.minor.proxy.jdk;

import com.hekrxe.plana.minor.proxy.MusicPlayer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class Client {
    public static void main(String[] args) {
        listenToMusic("陈奕迅");
    }

    private static void listenToMusic(String author) {
        // 刁钻的客户想听音乐（他才不关心谁家播放器好呢）
        // 所以此时对于他来说： 只需两步
        // 1 获取播放器
        MusicPlayer player = getPlayer();
        // 2 播放就行了
        System.out.println(player.play(author));
    }

    private static MusicPlayer getPlayer() {
        return new MusicProxy().get();
    }
}
