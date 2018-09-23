package com.asb2x;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: tanhuayou
 * Date: 2018/9/6
 */

@Configuration
public class Config implements InitializingBean {

    @Value("${helloPlaceholder}")
    public String hello;

    @Value("${helloMyPh}")
    public String helloMyPh;


    public Config() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("hello=" + hello + ", helloMyPh=" + helloMyPh);
    }
}
