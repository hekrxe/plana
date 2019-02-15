package com.hekrxe.tts.method.annotation;

import com.hekrxe.tts.method.interceptor.BeanFactoryMethodAdvisor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author tanhuayou on 2019/02/15
 */
public class TtsMethodAnnotationBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        this.advisor = new BeanFactoryMethodAdvisor();
    }
}
