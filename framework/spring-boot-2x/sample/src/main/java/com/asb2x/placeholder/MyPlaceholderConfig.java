package com.asb2x.placeholder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

/**
 * User: tanhuayou
 * Date: 2018/9/7
 */
@Configuration
public class MyPlaceholderConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        configurer.setProperties(fromOtherDevice());

        return configurer;
    }

    private Properties fromOtherDevice() {
        Properties myProperties = new Properties();
        myProperties.put("phValue", "phValue1111111");
        myProperties.put("helloMyPh", "helloMyPh1122222");

        return myProperties;
    }

}
