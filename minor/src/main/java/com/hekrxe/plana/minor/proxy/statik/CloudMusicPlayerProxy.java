package com.hekrxe.plana.minor.proxy.statik;

import com.hekrxe.plana.minor.proxy.MusicPlayer;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class CloudMusicPlayerProxy implements MusicPlayer {
    private CloudMusicPlayer cloudMusic;

    public CloudMusicPlayerProxy(CloudMusicPlayer cloudMusic) {
        this.cloudMusic = cloudMusic;
    }

    /**
     * 一般代理不执行 真正的 业务逻辑(播放音乐)  而是交给被代理的对象去执行
     * 一般代理是做一些铺路或善后的事情
     *
     * @param author 作者
     * @return 一般来说，代理商可以直接拒绝掉本次请求 或者返回一些其它结果
     */
    @Override
    public String play(String author) {
        System.out.println("由云音乐的代理 执行 音乐的播放，或者不给播放");
        return cloudMusic.play(author);
    }
}
