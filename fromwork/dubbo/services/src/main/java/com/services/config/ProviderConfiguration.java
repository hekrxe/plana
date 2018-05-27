package com.services.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 服务提供方
 *
 * @author hztanhuayou
 *  2018/2/10
 */
@Configuration
@Import({DubboConfigProperties.class, DubboCommonConfiguration.class})
class ProviderConfiguration {

    @Autowired
    private DubboConfigProperties properties;

    @Bean
    @ConditionalOnMissingBean(ProtocolConfig.class)
    public ProtocolConfig protocolConfig() {
        ProtocolConfig config = new ProtocolConfig();
        config.setPort(properties.getProtocolPort());
        config.setCharset("UTF-8");
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(ServicePostProcessor.class)
    public ServicePostProcessor serviceConfigPostProcessor() {
        return new ServicePostProcessor();
    }
}
