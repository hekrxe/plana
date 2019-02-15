package com.hekrxe.tts.method.interceptor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * @author tanhuayou on 2019/02/14
 */
public class BeanFactoryMethodAdvisor extends AbstractPointcutAdvisor {
    private final Pointcut pointcut = new MethodAttributeSourcePointcut();
    private final Advice advice = new TtsMethodInterceptor();

    /**
     * Get the Pointcut that drives this advisor.
     */
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }


    @Override
    public Advice getAdvice() {
        return advice;
    }
}
