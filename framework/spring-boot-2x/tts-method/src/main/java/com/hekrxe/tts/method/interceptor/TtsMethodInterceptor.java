package com.hekrxe.tts.method.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;

import java.lang.reflect.Method;

/**
 * @author tanhuayou on 2019/02/14
 */
public class TtsMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();

        Class<?> targetClass = getTargetClass(target);

        long t1 = System.currentTimeMillis();
        Object result = invocation.proceed();
        long t2 = System.currentTimeMillis();
        System.out.println(targetClass.getName() + "." + method.getName() + ": " + (t2 - t1));

        return result;
    }

    private Class<?> getTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        if (targetClass == null) {
            targetClass = target.getClass();
        }
        return targetClass;
    }
}
