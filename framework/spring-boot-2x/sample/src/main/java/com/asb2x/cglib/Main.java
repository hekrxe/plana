package com.asb2x.cglib;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * User: tanhuayou
 * Date: 2018/9/28
 */
public class Main {
    public static void main(String[] args) {

        // 被代理的对象
        UserService userService = new UserService();

        ProxyFactory proxyFactory = new ProxyFactory(userService);

        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
            Method method = invocation.getMethod();
            System.out.println(method.getName());
            Object[] arguments = invocation.getArguments();
            System.out.println(arguments.length);
            // 被代理的对象
            Object thisObject = invocation.getThis();

            System.out.println(thisObject);

            return method.invoke(thisObject, arguments);
        });

        UserService proxy = (UserService) proxyFactory.getProxy();
        System.out.println(proxy);

        String who = proxy.who();
        System.out.println(who);

    }
}
