package com.hekrxe.tts.method.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author tanhuayou on 2019/02/14
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyMethodConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TtsMethodAnnotationBeanPostProcessor methodAdvisor() {
        return new TtsMethodAnnotationBeanPostProcessor();
    }
}
