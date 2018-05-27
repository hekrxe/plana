package com.hekrxe.plana.minor.proxy.jdk;

import com.hekrxe.plana.minor.proxy.MusicPlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class MusicProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        System.out.println("我是网易云音乐的代理商，我通过XXX渠道得到了其版权，你可以听我放的音乐了");
        // 后期 我还可以改成QQ音乐 这样 其它任何代码都不需要变动了
        return "云音乐开始播放 " + args[0].toString() + " 的音乐";
    }

    public MusicPlayer get() {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{MusicPlayer.class}, this);
        System.out.println(proxy.getClass().getName());
        return (MusicPlayer) proxy;
    }

    @Override
    public String toString() {
        return "MusicProxy$ByJDK";
    }
}
