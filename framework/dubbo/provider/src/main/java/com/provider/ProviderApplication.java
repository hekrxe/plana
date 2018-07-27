package com.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.services.config.EnableDubboProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: tanhuayou
 * Date: 2018/5/24
 */

@RestController
@SpringBootApplication
@EnableDubboProvider
@DubboComponentScan(basePackageClasses = {ProviderApplication.class})
public class ProviderApplication implements InitializingBean {
    @Autowired
    ApplicationContext applicationContext;

    public ProviderApplication() {
        System.out.println(getClass().getName());
    }

    @RequestMapping(value = "/hi")
    public String hello() {
        ProviderApplication bean = applicationContext.getBean(ProviderApplication.class);
        System.out.println(bean.getClass().getName());
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(getClass().getName());
    }
}
