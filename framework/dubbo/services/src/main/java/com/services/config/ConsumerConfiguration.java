package com.services.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author hztanhuayou
 *  2018/2/10
 */
@Configuration
@AutoConfigureBefore({ReferenceAnnotationBeanPostProcessor.class})
@Import({DubboConfigProperties.class, DubboCommonConfiguration.class})
class ConsumerConfiguration {

    @Autowired
    private DubboConfigProperties properties;

    @Bean
    @ConditionalOnMissingBean(ConsumerConfig.class)
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(properties.getConsumerTimeout());
        consumerConfig.setRetries(0);
        return consumerConfig;
    }

    @Bean
    @ConditionalOnMissingBean(ReferenceBeforeProcessor.class)
    public ReferenceBeforeProcessor referenceBeforeProcessor() {
        return new ReferenceBeforeProcessor();
    }
}
