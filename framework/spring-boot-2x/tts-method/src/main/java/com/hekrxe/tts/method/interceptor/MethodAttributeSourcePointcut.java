package com.hekrxe.tts.method.interceptor;

import com.hekrxe.tts.method.annotation.TtsMethod;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author tanhuayou on 2019/02/14
 */
public class MethodAttributeSourcePointcut extends StaticMethodMatcherPointcut {

    private final long id = System.currentTimeMillis();

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return false;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        TtsMethod annotation = targetClass.getAnnotation(TtsMethod.class);
        if (null != annotation) {
            return true;
        }
        return null != method.getAnnotation(TtsMethod.class);
    }


    @Override
    public int hashCode() {
        return Long.hashCode(id) + MethodAttributeSourcePointcut.class.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MethodAttributeSourcePointcut)) {
            return false;
        }
        return id == ((MethodAttributeSourcePointcut) other).id;
    }
}
