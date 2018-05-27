package com.hekrxe.plana.minor.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CloudMusicPlayer 在毫不知情的情况下被代理了 被AOP了 也根本没有实例化他！
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class CloudMusicPlayerProxy implements MethodInterceptor {

    /**
     * All generated proxied methods call this method instead of the original method.
     * The original method may either be invoked by normal reflection using the Method object,
     * or by using the MethodProxy (faster).
     *
     * @param obj    "this", the enhanced object
     * @param method intercepted Method
     * @param args   argument array; primitive types are wrapped
     * @param proxy  used to invoke super (non-intercepted method); may be called
     *               as many times as needed
     * @return any value compatible with the signature of the proxied method. Method returning void will ignore this value.
     * @throws Throwable any exception may be thrown; if so, super method will not be invoked
     * @see MethodProxy
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("obj: " + obj.getClass().getName());
        System.out.println("method: " + method.getName());
        System.out.println("MethodProxy: " + proxy.getClass().getName());
        System.out.println("MethodProxy: SuperName" + proxy.getSuperName());
        return proxy.invokeSuper(obj, args);
    }

    public CloudMusicPlayer getObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CloudMusicPlayer.class);
        enhancer.setCallback(this);
        Object player = enhancer.create(new Class[]{String.class}, new Object[]{"人来人往"});
        System.out.println(player.getClass().getName());
        return (CloudMusicPlayer) player;
    }
}
