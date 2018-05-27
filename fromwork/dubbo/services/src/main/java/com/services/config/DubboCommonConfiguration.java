package com.services.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hztanhuayou
 * 2018/2/10
 */
@Configuration
class DubboCommonConfiguration {

    @Autowired
    private DubboConfigProperties properties;


    @Bean
    @ConditionalOnMissingBean(ApplicationConfig.class)
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(properties.getApplicationName());
        applicationConfig.setOrganization("arkr");
        applicationConfig.setLogger("slf4j");
        applicationConfig.setEnvironment("develop");

        return applicationConfig;
    }

    @Bean
    @ConditionalOnMissingBean(RegistryConfig.class)
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(properties.getRegistryAddress());
        registryConfig.setClient("curator");
        registryConfig.setGroup("arkr");
        return registryConfig;
    }
}
