package com.hekrxe.gts.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tanhuayou on 2019/02/15
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.hekrxe.gts.demo.service")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
