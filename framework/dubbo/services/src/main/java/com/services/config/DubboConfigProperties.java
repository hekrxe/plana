package com.services.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * User: tanhuayou
 * Date: 2018/3/3
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "dubbo")
@PropertySources({
        @PropertySource(value = "classpath:config/dubbo.properties"),
        @PropertySource(value = "classpath:config/application.properties"),
})
class DubboConfigProperties {
    /**
     * dubbo.properties
     */
    private Integer consumerTimeout;
    private String registryAddress;
    /**
     * application.properties
     */
    private String applicationName;
    private Integer protocolPort;

    public String getRegistryAddress() {
        return registryAddress;
    }

    public DubboConfigProperties setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
        return this;
    }

    public Integer getConsumerTimeout() {
        return consumerTimeout;
    }

    public DubboConfigProperties setConsumerTimeout(Integer consumerTimeout) {
        this.consumerTimeout = consumerTimeout;
        return this;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public DubboConfigProperties setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public Integer getProtocolPort() {
        return protocolPort;
    }

    public DubboConfigProperties setProtocolPort(Integer protocolPort) {
        this.protocolPort = protocolPort;
        return this;
    }
}
