package com.fw.proxy;

import javassist.util.proxy.MethodHandler;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by tanhuayou on 2019/01/18
 */
public class InvokeHandler implements InvocationHandler, MethodHandler, MethodInterceptor {

    private Object doInvoke(Object proxy, Method method, Object[] args) {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvokeHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        //TODO RPC调用
        return null;
    }

    /**
     * jdk invoke
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return doInvoke(proxy, method, args);
    }

    /**
     * byteBuddy invoke
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @RuntimeType
    public Object byteBuddyInvoke(@This Object proxy, @Origin Method method, @AllArguments @RuntimeType Object[] args) throws Throwable {
        return doInvoke(proxy, method, args);
    }

    /**
     * javassist invoke
     *
     * @param proxy
     * @param method
     * @param proceed
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Method proceed, Object[] args) throws Throwable {
        return doInvoke(proxy, method, args);
    }

    /**
     * cglib invoke
     *
     * @param proxy
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return doInvoke(proxy, method, args);
    }
}