package com.hekrxe.plana.minor.spi;

import com.sun.tools.javac.util.ServiceLoader;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class Main {
    public static void main(String[] args) {
        /**
         * ServiceLoader 加载 META-INF/services/ 下 文件名为 com.hekrxe.plana.minor.spi.Echo 的文件
         * 并认为该文件内的每一行都是 接口 com.hekrxe.plana.minor.spi.Echo 的一个实现类并加载它当你遍历时。
         */
        ServiceLoader<Echo> loader = ServiceLoader.load(Echo.class);
        for (Echo next : loader) {
            String echo = next.echo("Hello");
            System.out.println(echo);
        }
    }
}
